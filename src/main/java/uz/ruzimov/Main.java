package uz.ruzimov;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
  private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

  public static void main(String[] args) throws Exception {
    Random random = new Random();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate startDate = LocalDate.of(2024, 3, 31);
    LocalDate endDate = LocalDate.now();

    StringBuilder allContent = new StringBuilder();
    List<String> gitCommands = new ArrayList<>();

    System.out.println("🚀 Preparing commits...");

    LocalDate currentDate = startDate;
    while (!currentDate.isAfter(endDate)) {
      int commitCount = 5 + random.nextInt(11);

      for (int i = 0; i < commitCount; i++) {

        allContent.append("Commit made on ")
            .append(currentDate.format(formatter))
            .append(" - #")
            .append(i + 1)
            .append("\n");

        String commitDate = currentDate.format(formatter) + "T12:" +
            String.format("%02d", random.nextInt(59)) + ":00";

        gitCommands.add(String.format(
            "GIT_AUTHOR_DATE=\"%s\" GIT_COMMITTER_DATE=\"%s\" git commit -a -m \"Commit %d on %s\"",
            commitDate, commitDate, (i + 1), currentDate.format(formatter)
        ));
      }

      currentDate = currentDate.plusDays(1);
    }

    System.out.println(" Writing file...");
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("activity.txt", true))) {
      bw.write(allContent.toString());
    }

    System.out.println(" Executing " + gitCommands.size() + " commits in parallel...");
    ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    int batchSize = 100; // Git add batches
    for (int i = 0; i < gitCommands.size(); i += batchSize) {
      int end = Math.min(i + batchSize, gitCommands.size());
      List<String> batch = gitCommands.subList(i, end);

      runCommand("git add activity.txt");

      List<Future<?>> futures = new ArrayList<>();
      for (String cmd : batch) {
        futures.add(executor.submit(() -> {
          try {
            runCommandQuiet(cmd);
          } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
          }
        }));
      }

      for (Future<?> future : futures) {
        future.get();
      }

      System.out.println("✅ Processed " + end + "/" + gitCommands.size() + " commits");
    }

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.HOURS);

    System.out.println("\n🎉 All commits created successfully!");
    System.out.println("Now run: git push -u origin main");
  }

  private static void runCommand(String command) throws IOException, InterruptedException {
    ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
    pb.redirectErrorStream(true);
    Process process = pb.start();
    process.waitFor();
  }

  private static void runCommandQuiet(String command) throws IOException, InterruptedException {
    ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
    pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
    pb.redirectError(ProcessBuilder.Redirect.DISCARD);
    Process process = pb.start();
    process.waitFor();
  }
}