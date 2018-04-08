package com.shivamsingh.blog_lite.data

import java.util.concurrent.*
import javax.inject.Inject

open class JobExecutor @Inject constructor() : Executor {

    private val workQueue: LinkedBlockingQueue<Runnable>
    private val threadFactory: ThreadFactory
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        workQueue = LinkedBlockingQueue()
        threadFactory = JobThreadFactory()
        threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME.toLong(), KEEP_ALIVE_TIME_UNIT, workQueue, threadFactory)
    }

    override fun execute(command: Runnable?) {
        if (command == null) {
            throw IllegalArgumentException("Runnable to execute cannot be null")
        }
        this.threadPoolExecutor.execute(command)
    }

    class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, THREAD_NAME + counter++)
        }

        companion object {
            const val THREAD_NAME = "android_"
        }
    }

    companion object {
        private val INITIAL_POOL_SIZE = 3
        private val MAX_POOL_SIZE = 5

        // Sets the amount of time an idle thread waits before terminating
        private val KEEP_ALIVE_TIME = 10

        // Sets the Time Unit to seconds
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    }
}