package jokes.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jokes.beans.response.AsyncTask;
import jokes.utils.AsyncTaskManager;

@Component
public class AsyncTaskManager implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(AsyncTaskManager.class);

    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private static ConcurrentHashMap<String, AsyncTask> asyncTaskMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void start() {
        scheduledExecutorService.scheduleWithFixedDelay(this, 0, 5, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        try {
            long curTs = System.currentTimeMillis();
            Set<String> toDelete = new HashSet<>();
            for (Map.Entry<String, AsyncTask> entry : asyncTaskMap.entrySet()) {
                if(curTs < entry.getValue().getExpireTs()) {
                    continue;
                }

                logger.info("Prepare remove key {} for expire date {}", entry.getKey(), new Date(entry.getValue().getExpireTs()));
                toDelete.add(entry.getKey());
            }
            for (String key : toDelete) {
                asyncTaskMap.remove(key);
            }
        } catch (Exception e) {
            logger.error("ERROR ", e);
        } catch (Throwable e) {
            logger.error("ERROR ", e);
            throw e;
        }
    }

    public void putTask(AsyncTask asyncTask) {
        if(asyncTask.getExpireTs() == null) {
            throw new IllegalArgumentException("Task expireTs can not be null");
        }
        asyncTaskMap.put(asyncTask.getTaskId(), asyncTask);
    }

    public AsyncTask getTask(AsyncTask asyncTask) {
        return asyncTaskMap.get(asyncTask.getTaskId());
    }

    public AsyncTask getTask(String taskId) {
        return asyncTaskMap.get(taskId);
    }

    public void removeTask(AsyncTask asyncTask) {
        asyncTaskMap.remove(asyncTask.getTaskId());
    }
}
