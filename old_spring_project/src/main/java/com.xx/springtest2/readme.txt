创建五个线程Thread输出当前的时间.
升级: 用线程池 ThreadPoolExecutor 完成这个工作.
升级: 用spring管理线程池.

https://blog.csdn.net/lixiangchibang/article/details/110456200

线程池：
    1.corePoolSize : 表示线程池核心线程数，当初始化线程池时，会创建核心线程进入等待状态，即使它是空闲的，核心线程也不会被摧毁，从而降低了任务一来时要创建新线程的时间和性能开销。
    2.maximumPoolSize : 表示最大线程数，意味着核心线程数都被用完了，那只能重新创建新的线程来执行任务，但是前提是不能超过最大线程数量，否则该任务只能进入阻塞队列进行排队等候，直到有线程空闲了，才能继续执行任务。
    3.keepAliveTime : 表示线程存活时间，除了核心线程外，那些被新创建出来的线程可以存活多久。意味着，这些新的线程一但完成任务，而后面都是空闲状态时，就会在一定时间后被摧毁。
    4.unit : 存活时间单位，没什么好解释的，一看就懂。
    5.workQueue : 表示任务的阻塞队列，由于任务可能会有很多，而线程就那么几个，所以那么还未被执行的任务就进入队列中排队，队列我们知道是 FIFO 的，等到线程空闲了，就以这种方式取出任务。这个一般不需要我们去实现。