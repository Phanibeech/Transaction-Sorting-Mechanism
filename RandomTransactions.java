import java.util.Arrays;
import java.util.Date;
 
public class RandomTransactions{
 
   
    private final String MergeSort = "MergeSort";
    private final String QuickSort = "QuickSort";
    private final String HeapSort = "HeapSort";
 
    public static void main(String[] args) {
 
        RandomTransactions rt = new RandomTransactions();
 
        int noofobjs = 0;
 
        if (args.length > 0) {
            noofobjs = Integer.parseInt(args[0]);
        }
 
        rt.doExperiment(noofobjs);
    }
 
    private void doExperiment(int noofobjs) {
        int[] values;
        if (noofobjs != 0) {
            values = new int[]{noofobjs};
        } else {
            values = new int[]{1000, 10000, 100000, 1000000};
        }
 
        String[] sortAlgorithms = { MergeSort, QuickSort, HeapSort};
 
        System.out.printf( "%13s %13s %13s\n", "Number of Transactions | ","Sort Method | ", "Time Spent");
 
        for(int n = 0; n < values.length; n++) {
          Transaction[] transactions = generateRandomTransactions(values[n]);
 
            for(int i = 0; i < sortAlgorithms.length; i++) {
                Transaction[] copy = new Transaction[transactions.length];
                System.arraycopy(transactions, 0, copy, 0, transactions.length);
                Stopwatch timer = new Stopwatch();
                switch (sortAlgorithms[i]) {
                    case MergeSort:
                        Merge.sort(copy);
                        System.out.println("Mergesort compares & exchanges");
                        Merge.show(copy);
                        break;
                    case QuickSort:
                        Quick.sort(copy);
                        System.out.println("Quicksort compares & exchanges");
                        Quick.show(copy);
                        break;
                    case HeapSort:
                        Heap.sort(copy);
                        System.out.println(" Heapsort compares & exchanges");
                        Heap.show(copy);
                        break;
                }
                double runningTime = timer.elapsedTime();
 
                printResults(values[n], sortAlgorithms[i], runningTime);
            }
        }
    }
 
    private Transaction[] generateRandomTransactions(int noofobjs) {
        Transaction[] transactions = new Transaction[noofobjs];
 
        for(int i = 0; i < noofobjs; i++) {
            String who = "Client " + (i + 1);
 
            int month = StdRandom.uniform(12) + 1;
            int maxDay = 0;
            if(month==2) maxDay=28;
            else maxDay=30;
            int day = StdRandom.uniform(maxDay) + 1;
            int year = StdRandom.uniform(1903, 2021);
            Date date = new Date(month, day, year);
 
            double amount = (double) Math.round(StdRandom.uniform(0.0, 1000000.0) * 100) / 100;
 
            Transaction transaction = new Transaction(who, date, amount);
            transactions[i] = transaction;
        }
 
        return transactions;
    }
 
    private void printResults(int numberOfTransactions, String sortMethod, double timeSpent) {
        System.out.printf("%22d %14s %16.2f\n", numberOfTransactions, sortMethod, timeSpent);
    }
 
 
}