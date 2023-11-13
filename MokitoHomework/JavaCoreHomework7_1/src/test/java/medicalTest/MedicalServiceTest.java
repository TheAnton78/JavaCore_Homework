package medicalTest;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.Main;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;

public class MedicalServiceTest {
    @Test
    void checkBloodPressureTest(){
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById(Mockito.anyString()))
                .thenReturn(new PatientInfo("Aртем", "Михайлов", LocalDate.of(1982, 1, 16),
                        new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78))));

        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, alertService);
        medicalService.checkBloodPressure("d5b13e5e-f526-4659-8fa1-2b0378e15779", new BloodPressure(125, 75));
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(alertService).send(argumentCaptor.capture());
        MatcherAssert.assertThat(argumentCaptor.getValue(), allOf(startsWith("Warning, patient with id:"), endsWith("need help")));

    }

    @Test
    void checkTemperatureTest(){
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById(Mockito.anyString()))
                .thenReturn(new PatientInfo("Александр", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, alertService);
        medicalService.checkTemperature("db13e5e-f526-4659-8fa1-2b0378e15779", new BigDecimal("32.0"));
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(alertService).send(argumentCaptor.capture());
        MatcherAssert.assertThat(argumentCaptor.getValue(), allOf(startsWith("Warning, patient with id:"), endsWith("need help")));

    }

    @Test
    void testWithNormalParam(){
        PatientInfoRepository patientInfoFileRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoFileRepository.getById(Mockito.anyString()))
                .thenReturn(new PatientInfo("Александр", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        MedicalService medicalService = new MedicalServiceImpl(patientInfoFileRepository, alertService);
        medicalService.checkBloodPressure("d5b13e5e-f526-4659-8fa1-2b0378e15779", new BloodPressure(120, 80));
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(alertService, Mockito.never()).send(argumentCaptor.capture());
        medicalService.checkTemperature("d5b13e5e-f526-4659-8fa1-2b0378e15779", new BigDecimal("36.65"));
        Mockito.verify(alertService, Mockito.never()).send(argumentCaptor.capture());



    }
}
