import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minors = persons.stream()
                .filter(person -> person.getAge() <= 18)
                .count();

        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());

        Collection<Person> workers = persons.stream()
                .filter(person -> {if(person.getSex().equals(Sex.MAN)) {
                                        return person.getAge() >= 18 && person.getAge() <= 65;}
                                        else{
                                       return person.getAge() >= 18 && person.getAge() <= 60;
                                    }
                                   }
                            )
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.printf("Количество несовершеннолетних: %d\n", minors);
        System.out.println("Первые десять в списке военнообязанных: ");
        conscripts.stream()
                .limit(10)
                .forEach(System.out::println);
        System.out.println("Первые десять в списке роботоспособных: ");
        workers.stream()
                .limit(10)
                .forEach(System.out::println);
    }
}
