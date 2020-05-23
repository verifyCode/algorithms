import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author xjn
 * @since 2020-05-22
 */
public class CompletableFutureTest {

    //1.新建一个完成的CompletableFuture
    static void completedFutureExample() throws Exception {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        if (cf.isDone()) {
            System.out.println(cf.get());
        }
    }

    //2.运行一个简单的异步stage
    static void runAsyncExample() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            sleep(1000);
        });
        System.out.println(completableFuture.isDone());
    }

    //3.将方法作用于前一个Stage
    static void thenApplyExample() throws Exception {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message").thenApply(s -> s.toUpperCase());

        if (message.isDone()) {
            System.out.println(message.get());
        }
    }

    //4.异步的的将方法作用于前一个Stage
    static void thenApplyAsyncExample() {
        CompletableFuture completableFuture = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                    sleep(1000);
                    return s.toLowerCase();
                });
        System.out.println(completableFuture.join());

    }

    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    //使用一个自定义的Executor来异步执行该方法
    static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                    sleep(1000);
                    return s += "123213";
                }, executor);
        System.out.println(cf.join());
    }

    //6.消费(Consume)前一个Stage的结果
    static void thenAcceptExample() throws Exception {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> completableFuture = CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));
        System.out.println(completableFuture.get());
        System.out.println(result.toString());

    }

    //7.异步执行Comsume
    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));

        cf.join();
        System.out.println(result.toString());
    }

    //8.计算出现异常时
    //TODO
    static void completeExceptionallyExample() throws Exception {
//        CompletableFuture cf = CompletableFuture.completedFuture("message")
//                .thenApplyAsync(String::toUpperCase,
//                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//
//        CompletableFuture exceptionHandler =
//                cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
    }

    //9.取消计算
    static void cancelExample() {

    }

    //10.将Function作用于两个已完成Stage的结果之一
    static void applyToEitherExample() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> {
                    sleep(100);
                    return s.toLowerCase() + "1";
                });
        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> {
                    sleep(100);
                    return s.toUpperCase();
                }),
                s -> s + " from applyToEither");
        System.out.println(cf2.join());
    }

    //11.消费两个阶段的任意一个结果
    static void acceptEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
    }

    //12.在两个阶段都完成后运行Runnable
    static void runAfterBothExample() {
    }

    public static void main(String[] args) throws Exception {
//        completedFutureExample();
//        runAsyncExample();
//        thenApplyExample();
//        thenApplyAsyncExample();
//        thenApplyAsyncWithExecutorExample();
//        thenAcceptExample();
//        completeExceptionallyExample();
//        applyToEitherExample();
        runAfterBothExample();
    }


    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }
}
