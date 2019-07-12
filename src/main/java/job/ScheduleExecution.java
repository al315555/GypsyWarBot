package job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public final class ScheduleExecution {
    private static final int INTERVAL_MINUTES = 5;
    private static final int INTERVAL_HOURS = 4;
    public static final void execute(){
        try {
            JobDetail job1 = JobBuilder.newJob(BattleJob.class).withIdentity("battleVS", "1Group").build();
            JobDetail awakeJob = JobBuilder.newJob(AwakeJob.class).withIdentity("awakeJob", "2Group").build();

            Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("simpleTriggerAwake", "2Group")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(INTERVAL_MINUTES)).build();
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(awakeJob, trigger1);

            Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("simpleTriggerAlgorithm", "1Group")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(INTERVAL_HOURS)).build();
            Scheduler scheduler2 = new StdSchedulerFactory().getScheduler();
            scheduler2.start();
            scheduler2.scheduleJob(job1, trigger2);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
