package il.ac.tau.cs.sw1.ex7.histogram;

import java.util.*;

public class HashMapHistogramTest {
    static IHistogram<Integer> hInt = new HashMapHistogram<>();
    static IHistogram<String> hString = new HashMapHistogram<>();

    static Map<Integer,Integer> resInt = new HashMap<>();
    static Map<String,Integer> resString = new HashMap<>();

    static final String buffer = " _            _   \n" +
            "| |_ ___  ___| |_ \n" +
            "| __/ _ \\/ __| __|\n" +
            "| ||  __/\\__ \\ |_ \n" +
            " \\__\\___||___/\\__|\n";

    static IHistogram<String> h1 = new HashMapHistogram<>();
    static IHistogram<String> h2 = new HashMapHistogram<>();
    static IHistogram<String> h3 = new HashMapHistogram<>();
    static String VALUE_ERROR_MESSAGE = "";
    //@pre: getCountForItem has no bugs
    public static void addItem() {
        int[] a = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 9};
        resInt.put(1, 1);
        resInt.put(2, 2);
        resInt.put(3, 3);
        resInt.put(4, 4);
        resInt.put(5, 5);
        resInt.put(6, 4);
        resInt.put(7, 3);
        resInt.put(8, 2);
        resInt.put(9, 1);
        resInt.put(20, 0);
        for (int i : a) {
            hInt.addItem(i);
        }
        Assert.assertHistEquals(hInt, resInt);
        String[] b = {"one","two","three","one","two","three","drink"};
        resString.put("one", 2);
        resString.put("two", 2);
        resString.put("three", 2);
        resString.put("drink", 1);
        for (String s : b){
            hString.addItem(s);
        }
        Assert.assertHistEquals(hString, resString);
    }

    public static void removeItem() {
        int cur_place = 0;
        try {
            hInt.removeItem(2);
            Assert.assertEquals( 0,hInt.getCountForItem(2));
            cur_place++;
            hInt.removeItem(5);
            Assert.assertEquals( 0,hInt.getCountForItem(5));
            cur_place++;
            hInt.removeItem(42);
            Assert.printValueIllegalError(42);
        } catch (IllegalItemException e) {
            if (cur_place == 2) {
                //TODO good job
            } else {
                Assert.printValueLegalError(cur_place==0 ? 2:5);
            }
        }

        cur_place = 0;
        try {
            hString.removeItem("two");
            Assert.assertEquals(0,hString.getCountForItem("two"));
            Assert.assertEquals(2,hString.getCountForItem("one"));
            cur_place++;
            hString.removeItem("shlookypookie");
            Assert.printValueIllegalError("shlookypookie");
        } catch (IllegalItemException e) {
            if (cur_place == 1) {
                //TODO good job
            } else {
                Assert.printValueLegalError("two");
            }
        }
    }

    public static void addItemKTimes() {
        hInt = new HashMapHistogram<>();
        hString = new HashMapHistogram<>();
        resInt.clear();
        resString.clear();

        resInt.put(1, 6);
        resInt.put(2, 3);
        resInt.put(3, 5);
        resInt.put(4, 1);
        resInt.put(5, 1);
        resInt.put(6, 11);
        resInt.put(7, 1);
        resInt.put(8, 1);
        resInt.put(9, 1);
        for (Integer item :
                resInt.keySet()) {
            try {
                hInt.addItemKTimes(item, resInt.get(item));
            } catch (IllegalKValueException e) {
                Assert.printKLegalError(resInt.get(item));
            }
        }
        Assert.assertHistEquals(hInt, resInt);
        try {
            hInt.addItemKTimes(69, 0);
            Assert.printKIllegalError(0);
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }

        try {
            hInt.addItemKTimes(70, -42);
            Assert.printKIllegalError(-42);
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }

        resString.put("is", 42);
        resString.put("this", 420);
        resString.put("real", 123);
        resString.put("life", 3);
        //is this
        resString.put("just", 654);
        resString.put("fantasy", 88);
        for (String item : resString.keySet()){
            try {
                hString.addItemKTimes(item, resString.get(item));
            } catch (IllegalKValueException e) {
                Assert.printKLegalError(resString.get(item));
            }
        }

        Assert.assertHistEquals(hString, resString);
        try {
            hString.addItemKTimes("shloop", 0);
            Assert.printKIllegalError(0);
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }

        try {
            hString.addItemKTimes("shmoop", -42);
            Assert.printKIllegalError(-42);
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }
    }

    public static void removeItemKTimes(){
        hInt = new HashMapHistogram<>();
        hString = new HashMapHistogram<>();
        resString.clear();
        resInt.clear();

        resInt.put(1, 2);
        resInt.put(2, 17);
        resInt.put(5, 9);

        for (Integer item :
                resInt.keySet()) {
            try {
                hInt.addItemKTimes(item, resInt.get(item));
            } catch (IllegalKValueException e) {
                Assert.printKLegalError(resInt.get(item));
            }
        }
        resInt.remove(1);
        resInt.remove(5);
        resInt.put(2, 1);
        try {
            hInt.removeItemKTimes(1, 0);
            Assert.printKIllegalError(0);
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }
        catch (IllegalItemException e){
            Assert.printItemLegalError(1);
        }

        try {
            hInt.removeItemKTimes(5, 10);
            Assert.printKIllegalError(10);
            System.out.println("(there are only 9 times that this object should be)");
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }
        catch (IllegalItemException e){
            Assert.printItemLegalError(5);
        }

        try {
            hInt.removeItemKTimes(50, 0);
            Assert.printKIllegalError(0);
        }
        catch (IllegalKValueException e){
            System.out.println("should first address that the item is not legal.\nitem = 50\tk = 0");
        }
        catch (IllegalItemException e){
            //TODO good exception
        }

        try {
            hInt.removeItemKTimes(1, 2);
            hInt.removeItemKTimes(5, 9);
            hInt.removeItemKTimes(2, 16);
        }
        catch (IllegalKValueException|IllegalItemException e){
            System.out.println("threw error while removing existing items with legal k's");
        }
        Assert.assertHistEquals(hInt, resInt);

        resString.put("itsy", 45);
        resString.put("bitsy", 9999);
        resString.put("spider", 78996);

        for (String item : resString.keySet()){
            try {
                hString.addItemKTimes(item, resString.get(item));
            } catch (IllegalKValueException e) {
                Assert.printValueLegalError(item);
            }
        }
        resString.put("spider", 6);
        resString.put("bitsy", 9990);
        resString.remove("itsy");

        try {
            hString.removeItemKTimes("itsy", 0);
            Assert.printKIllegalError(0);
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }
        catch (IllegalItemException e){
            Assert.printItemLegalError("itsy");
        }

        try {
            hString.removeItemKTimes("itsy", 100);
            Assert.printKIllegalError(100);
            System.out.println("(there are only 45 times that this object should be)");
        }
        catch (IllegalKValueException e){
            //TODO good exception
        }
        catch (IllegalItemException e) {
            Assert.printItemLegalError("itsy");
        }

        try {
            hString.removeItemKTimes("itsush", 100);
        }
        catch (IllegalKValueException e){
            System.out.println("should first address that the item is not legal.\nitem = \"itsush\"\tk = 100");
        }
        catch (IllegalItemException e){
            //TODO good exception
        }

        try {
            hString.removeItemKTimes("itsy", 45);
            hString.removeItemKTimes("bitsy", 9);
            hString.removeItemKTimes("spider", 78990);
        }
        catch (IllegalKValueException|IllegalItemException e){
            System.out.println("threw error while removing existing items with legal k's");
        }
        Assert.assertHistEquals(hInt, resInt);
    }

    public static void getCountForItem(){
        hInt = new HashMapHistogram<>();
        hString = new HashMapHistogram<>();
        resInt.clear();
        resString.clear();
        for (int i = 0; i < 20; i++) {
            resInt.put(i, (i-5)*(i-5)+1);
        }
        for (Integer item :
                resInt.keySet()) {
            try {
                hInt.addItemKTimes(item, resInt.get(item));
            } catch (IllegalKValueException e) {
                Assert.printItemLegalError(resInt.get(item));
            }
        }
        Assert.assertHistEquals(hInt, resInt);
    }

    public static void addAll(){
        hInt = new HashMapHistogram<>();
        Collection<Integer> addInt = new ArrayList<>();

        int[] items = new int[]{1,2,3,4,5,6,1,1,2,3,4,5,1,6,5,8,9,7,5,6,3,11,10,9};
        for (int item : items)
        {
            addInt.add(item);
        }
        resInt.clear();
        resInt.put(1, 4);
        resInt.put(2, 2);
        resInt.put(3, 3);
        resInt.put(4, 2);
        resInt.put(5, 4);
        resInt.put(6, 3);
        resInt.put(7, 1);
        resInt.put(8, 1);
        resInt.put(9, 2);
        resInt.put(10, 1);
        resInt.put(11, 1);

        hInt.addAll(addInt);
        Assert.assertHistEquals(hInt, resInt);
        hInt.addAll(addInt);
        //אינטליג'יי החליט לעשות את זה לבד, השורה הזאת מכפילה את כל הערכים בresInt
        resInt.replaceAll((k, v) -> resInt.get(k) * 2);
        Assert.assertHistEquals(hInt, resInt);

    }

    public static void clear(){
        resInt.clear();
        resString.put("is", 42);
        resString.put("this", 420);
        resString.put("real", 123);
        resString.put("life", 3);
        //is this
        resString.put("just", 654);
        resString.put("fantasy", 88);
        for (String item : resString.keySet()) {
            try {
                hString.addItemKTimes(item, resString.get(item));
            } catch (IllegalKValueException e) {
                Assert.printKLegalError(resString.get(item));
            }
        }
        hString.clear();
        for (String word : resString.keySet()){
            if (hString.getCountForItem(word)!=0){
                System.out.println("there were still words after clear has benn called");
            }
        }

    }

    public static void getItemSet(){
        resString.put("is", 42);
        resString.put("this", 420);
        resString.put("real", 123);
        resString.put("life", 3);
        //is this
        resString.put("just", 654);
        resString.put("fantasy", 88);
        for (String item : resString.keySet()) {
            try {
                hString.addItemKTimes(item, resString.get(item));
            } catch (IllegalKValueException e) {
                Assert.printKLegalError(resString.get(item));
            }
        }
        Assert.assertSetEquals(resString.keySet(), hString.getItemsSet());
    }

    public static void merge() {
        h1 = new HashMapHistogram<>();
        h2 = new HashMapHistogram<>();
        h3 = new HashMapHistogram<>();
        List<String> l1 = stringToList(
                "wake me up " +
                        "wake me up inside " +
                        "i cant wake up " +
                        "wake me up inside " +
                        "save me " +
                        "from this subject ");
        List<String> l2 = stringToList(
                "So wake me up when it's all over " +
                        "When I'm wiser and I'm older " +
                        "All this time I was finding myself " +
                        "And I didnt know I was lost ");
        List<String> l3 = stringToList(
                "Don't stop, make it pop " +
                        "DJ, blow my speakers up " +
                        "Tonight, I'ma fight " +
                        "'Til we see the sunlight " +
                        "Tick-tock on the clock " +
                        "But the party don't stop, no " +
                        "Oh, whoa, whoa-oh " +
                        "Oh, whoa, whoa-oh " +
                        "Don't stop, make it pop " +
                        "DJ, blow my speakers up " +
                        "Tonight, I'ma fight " +
                        "'Til we see the sunlight " +
                        "Tick-tock on the clock " +
                        "But the party don't stop, no " +
                        "Oh, whoa, whoa-oh " +
                        "Oh, whoa, whoa-oh"
        );
        hString = new HashMapHistogram<>();
        h2.addAll(l2);
        h3.addAll(l3);
        hString.addAll(l1);
        hString.merge(h2);
        hString.merge(h3);
        resString.clear();
        resString.put("up", 7);
        resString.put("the", 6);
        resString.put("wake", 5);
        resString.put("me", 5);
        resString.put("i", 4);
        resString.put("don't", 4);
        resString.put("stop,", 4);
        resString.put("oh,", 4);
        resString.put("whoa,", 4);
        resString.put("whoa-oh", 4);
        resString.put("inside", 2);
        resString.put("this", 2);
        resString.put("when", 2);
        resString.put("all", 2);
        resString.put("i'm", 2);
        resString.put("and", 2);
        resString.put("was", 2);
        resString.put("make", 2);
        resString.put("it", 2);
        resString.put("pop", 2);
        resString.put("dj,", 2);
        resString.put("blow", 2);
        resString.put("my", 2);
        resString.put("speakers", 2);
        resString.put("tonight,", 2);
        resString.put("i'ma", 2);
        resString.put("fight", 2);
        resString.put("'til", 2);
        resString.put("we", 2);
        resString.put("see", 2);
        resString.put("sunlight", 2);
        resString.put("tick-tock", 2);
        resString.put("on", 2);
        resString.put("clock", 2);
        resString.put("but", 2);
        resString.put("party", 2);
        resString.put("no", 2);
        resString.put("cant", 1);
        resString.put("save", 1);
        resString.put("from", 1);
        resString.put("subject", 1);
        resString.put("so", 1);
        resString.put("it's", 1);
        resString.put("over", 1);
        resString.put("wiser", 1);
        resString.put("older", 1);
        resString.put("time", 1);
        resString.put("finding", 1);
        resString.put("myself", 1);
        resString.put("didnt", 1);
        resString.put("know", 1);
        resString.put("lost", 1);
        Assert.assertHistEquals(hString, resString);
    }

    public static void iterator() {
        int i = 0;
        Iterator<String> it = hString.iterator();
        List<String> expected = new ArrayList<>();
        expected.add("up");
        expected.add("the");
        expected.add("me");
        expected.add("wake");
        expected.add("don't");
        expected.add("i");
        expected.add("oh,");
        expected.add("stop,");
        expected.add("whoa,");
        expected.add("whoa-oh");
        expected.add("'til");
        expected.add("all");
        expected.add("and");
        expected.add("blow");
        expected.add("but");
        expected.add("clock");
        expected.add("dj,");
        expected.add("fight");
        expected.add("i'm");
        expected.add("i'ma");
        expected.add("inside");
        expected.add("it");
        expected.add("make");
        expected.add("my");
        expected.add("no");
        expected.add("on");
        expected.add("party");
        expected.add("pop");
        expected.add("see");
        expected.add("speakers");
        expected.add("sunlight");
        expected.add("this");
        expected.add("tick-tock");
        expected.add("tonight,");
        expected.add("was");
        expected.add("we");
        expected.add("when");
        expected.add("cant");
        expected.add("didnt");
        expected.add("finding");
        expected.add("from");
        expected.add("it's");
        expected.add("know");
        expected.add("lost");
        expected.add("myself");
        expected.add("older");
        expected.add("over");
        expected.add("save");
        expected.add("so");
        expected.add("subject");
        expected.add("time");
        expected.add("wiser");

        List<String> actual = new ArrayList<>();
        for (String word :
                hString) {
            actual.add(word);
        }

        Assert.assertListEquals(expected,actual);

    }
    public static void enter(String name){
        System.out.println("=================================================");
        System.out.println("Starting test for "+name+"\n");
    }
    public static void exit(String name){
        System.out.println("\nFinished test for "+name);
        System.out.println("=================================================");
        System.out.println(buffer);

    }
    public static void main(String[] args) {

        enter("addItem");
        addItem();
        exit("addItem");

        enter("removeItem");
        removeItem();
        exit("removeItem");

        enter("addItemKTimes");
        addItemKTimes();
        exit("addItemKTimes");

        enter("removeItemKTimes");
        removeItemKTimes();
        exit("removeItemKTimes");

        enter("getCountForItem");
        getCountForItem();
        exit("getCountForItem");

        enter("addAll");
        addAll();
        exit("addAll");

        enter("clear");
        clear();
        exit("clear");

        enter("getItemSet");
        getItemSet();
        exit("getItemSet");

        enter("merge");
        merge();
        exit("merge");

        enter("iterator");
        iterator();
        exit("iterator");
    }

    public static List<String> stringToList(String st){
        List<String> l = new ArrayList<>();
        for (String word :
                st.split(" ")) {
            l.add(word.toLowerCase());
        }
        return l;
    }

    public static class Assert{
        public static void assertEquals(Object a, Object b,String msg){
            if (!a.equals(b)){
                printError(msg, a, b);
            }
        }

        public static void assertEquals(Object a, Object b){
            if (!a.equals(b)){
                printError(VALUE_ERROR_MESSAGE, a, b);
            }
        }

        public static <T> void assertHistEquals(IHistogram<T> a, Map<T,Integer> b) {
            for (T item : b.keySet()){
                if (b.get(item)!=a.getCountForItem(item)){
                    printError("histogram has wrong count for "+item, b.get(item), a.getCountForItem(item));
                    Exception e = new Exception();
                    e.printStackTrace(System.out);
                    break;
                }
            }
        }

        public static void assertSetEquals(Set<String> a, Set<String> b){
            List<String> l1 = new ArrayList<>(a);
            Collections.sort(l1);
            List<String> l2 = new ArrayList<>(b);
            Collections.sort(l2);
            if (l1.size()!=l2.size()){
                printError("sets size differ", l1, l2);
                return;
            }
            int index = -1;
            for (int i = 0; i < l1.size(); i++) {
                if (!l1.get(i).equals(l2.get(i))){
                    index = i;
                    break;
                }
            }
            if (index!=-1){
                printError("first incompatabillity at "+index,
                           addErrorToArray((String[])l1.toArray(), index),
                           addErrorToArray((String[])l2.toArray(), index));
            }

        }

        public static void printError(String msg, Object o1, Object o2){
            System.out.println(msg+"\n" +
                                       "Expected  : "+o1+
                                       "\nActual    : "+o2);

        }

        public static void printKLegalError(int k){
            System.out.println("operation with legal k failed.\nk = "+k);
        }

        public static void printKIllegalError(int k){
            System.out.println("operation with illegal k succeeded.\nk = "+k);
        }
        static String addErrorToArray(Object[] arr1, int i){
            StringBuilder res= new StringBuilder("[");
            for (int j = 0; j < arr1.length; j++) {
                if (j!=0){
                    res.append(",");
                }
                if (j==i){
                    res.append(String.format(" <<<%s>>>", arr1[j]));
                }
                else {
                    res.append(String.format(" %s", arr1[j]));
                }
            }
            res.append("]");
            return res.toString();
        }

        public static <T> void printValueIllegalError(T item) {
            System.out.println("removed item that was not in the histogram. should throw error.\nitem = "+item);
            Exception e = new Exception();
            e.printStackTrace(System.out);
        }

        public static <T> void printValueLegalError(T item) {
            System.out.println("could not remove item that was in histogram. threw error and it shouldn't\nitem = "+ item);
            Exception e = new Exception();
            e.printStackTrace(System.out);
        }

        public static <T> void printItemLegalError(T item) {
            System.out.println("operation with legal item failed.\nitem = "+item);
            Exception e = new Exception();
            e.printStackTrace(System.out);
        }

        public static void assertListEquals(List<String> expected, List<String> actual) {
            if (expected.size()!=actual.size()){
                printError("sets size differ", expected, actual);
                return;
            }
            int index = -1;
            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(actual.get(i))){
                    index = i;
                    break;
                }
            }
            if (index!=-1){
                printError("first incompatabillity at "+index,
                           addErrorToArray(expected.toArray(), index),
                           addErrorToArray(actual.toArray(), index));
            }
        }
    }
}
