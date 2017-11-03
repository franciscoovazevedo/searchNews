import java.util.LinkedList;
// primeira versão // os workers serão lançados independentemente do servidor e do client. 
// receberão um PORTO (do servidor) e depois vão esperar até terem trabalho para fazer.
public class Worker {
	private LinkedList<Task> tasks = new LinkedList<>();
	
	public Worker(){
	}
	
	public void assignTask(Task task) {
		tasks.add(task);
	}
	
	public LinkedList<Result> workListOfTasks() {
		LinkedList<Result> success = new LinkedList<>();
		for (Task task : tasks) {
			if(work(task) != null)
				success.add(work(task));
		}
		
		return success;
	}

	public Result work(Task task){
		if(task.existsWord())
			return new Result(task.getNewsId(), task.countsWords());
		else
			return null;
	}
	
	public String getTaskDescription() {
		for (Task task : tasks) {
			return task.toString();
		}
		
		return "Done";
	}
	
	public LinkedList<Task> getTasks() {
		return tasks;
	}
}
