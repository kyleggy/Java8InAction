package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class TraderTransactionPractise {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> sortedTransactions = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());

        sortedTransactions.forEach(System.out::println);

        //2. What are all the unique cities where the traders work?
        List<String> uniqueCities = transactions.stream().map(Transaction::getTrader).distinct()
                .map(Trader::getCity).distinct().collect(Collectors.toList());

        System.out.println(uniqueCities);

        //2.2 Optimize
        List<String> uniqueOptimizeCities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueOptimizeCities);

        //3. Find all traders from Cambridge and sort them by name.
        List<Trader> combridgeTraders= transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader).distinct().sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(combridgeTraders);

        //4. Return a string of all traders’ names sorted alphabetically

        String name = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct()
                .sorted().reduce("", (s, s2) -> s + s2);

        System.out.println(name);

        //5. Are any traders based in Milan?
        boolean isInMilan = transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .anyMatch("Milan"::equals);
        System.out.println("Are any traders based in Milan? : " + isInMilan);

        // Query 5: Are there any trader based in Milan?

        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan")
                        );
        System.out.println(milanBased);

        //6. Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(transaction ->
                transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);


        //7. What’s the highest value of all the transactions?
        Integer highestValue = transactions.stream().map(Transaction::getValue)
                .max(Comparator.comparingInt(o -> o)).get();
        System.out.println(highestValue);

        //Use reduce
        highestValue = transactions.stream().map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(highestValue);


        //8. Find the transaction with the smallest value.
        int smallestValue = transactions.stream().map(Transaction::getValue)
                .min(Comparator.comparingInt(o -> o)).get();
        System.out.println(smallestValue);
        Transaction smallestValueTransaction = transactions.stream().filter(transaction -> transaction.getValue() == smallestValue)
                .findFirst().get();
        System.out.println(smallestValueTransaction);

        //Use reduce
        smallestValueTransaction = transactions.stream()
                .reduce(smallestValueTransaction, (transaction, transaction2) ->
                {
                    if (transaction.getValue() > transaction2.getValue()) {
                        return transaction2;
                    } else {
                        return transaction;
                    }
                });

        System.out.println(smallestValueTransaction);

        //better
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .min(comparing(Transaction::getValue));
        System.out.println(smallestTransaction.get());

    }
}