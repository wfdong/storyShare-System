package jokes.beans.response;

public class AsyncTask {

	private String taskId;

    private Long commitTs;

    private Long expireTs;

    private Object content;

    private Object msg;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getCommitTs() {
        return commitTs;
    }

    public void setCommitTs(Long commitTs) {
        this.commitTs = commitTs;
    }

    public Long getExpireTs() {
        return expireTs;
    }

    public void setExpireTs(Long expireTs) {
        this.expireTs = expireTs;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
