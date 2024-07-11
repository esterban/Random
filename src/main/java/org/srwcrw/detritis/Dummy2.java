package org.srwcrw.detritis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Dummy2 {
//        extends Dummy {

    static class Stuff {
        public static String out() {
            return "Stuff";
        }
    }

    static class Stuff2 extends Stuff {
        public static String out() {
            return "Stuff";
        }
    }

    int m_a;

    public Dummy2() {
        super();
    }

    public Dummy2(int a) {
        this();
        m_a = a;


    }

    static int t = 10;

    public void meth() {
        System.out.println(this.t);
    }


    abstract class ABbl
    {

//        abstract int a;
//        abstract void meth() {
//
//        }
    }

    interface Inte {
        BigDecimal big = new BigDecimal(0.00);
    }

    class ClassA {
        protected void metha() throws ClassNotFoundException {

        }
    }

    class ClasB extends ClassA {
        @Override
        protected void metha() throws ClassNotFoundException {
            Class a = Class.forName("java.lang.String");
//            a.getMethod()
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        List<String> list = new ArrayList<>();
        list.add(0,"a");

        Supplier<String> i =() -> "Car";
        Consumer<String> c = x -> System.out.print(x.toLowerCase());
        Consumer<String> d = x -> System.out.print(x.toUpperCase());
        c.andThen(d).accept(i.get());

        LocalDate localDate = LocalDate.of(2015,4,4);
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("E, MMM dd, yyyy")));
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));

        List<String> stringList = Arrays.asList("dog", "over", "good");

//        System.out.println(stringList.stream().reduce(new Character(), (Character s1, s2) -> s1 + s2.char );
        stringList.stream().reduce((x1,x2) -> x1.length() == 3 ? x1:x2).ifPresent(System.out::println);
        stringList.stream().reduce((x1,x2) -> x1.length() > x2.length()? x1:x2).ifPresent(System.out::println);

        System.out.println(stringList.stream().reduce(new String(), (x1,x2) -> {if (x1.equals("dog")) return x1;return x2;}));

//        stringList.stream().reduce( )

        Integer n1 = 1;
        Integer n2 = n1;
        n1+=1;

        System.out.println(n1);
        System.out.println(n2);

        ByteArrayOutputStream b1 = new ByteArrayOutputStream(10);
        b1.write(100);

        b1.flush();

        System.out.println(b1.size());

//        System.out.println((Stuff2)Stuff.out());

        Stream<String> stringStream = List.of("one", "two", "three").stream();

        Collector<String , ? , HashMap<Integer, String>> collector = Collector.of(HashMap::new, (s,t) -> s.put(t.length(), t.toLowerCase()), (x,y) -> {x.putAll(y); return x;});

        Map<Integer, String> resultMap = stringStream.collect(collector);

        YearMonth ym1 = YearMonth.now();

        resultMap.forEach((k,v) -> System.out.println(k + " -> " + v));
//        ym1.getMonthValue()

//        ym1.until(ym1);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = simpleDateFormat.parse("2015-01-15");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(calendar.get(Calendar.MONTH));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate1 = LocalDate.parse("2012-01-15", dateTimeFormatter);

        System.out.println(localDate1.getMonthValue());
    }
}
