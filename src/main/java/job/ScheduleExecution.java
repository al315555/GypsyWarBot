package job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public final class ScheduleExecution {
    public static final void execute(){
        try {
            JobDetail job1 = JobBuilder.newJob(BattleJob.class).withIdentity("battleVS", "uniqueGroup").build();

            Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("simpleTriggerAlgorithm", "uniqueGroup")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(job1, trigger1);

            /*JobDetail job2 = JobBuilder.newJob(ByeJob.class).withIdentity("byeJob", "group2").build();
            Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("cronTrigger", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            Scheduler scheduler2 = new StdSchedulerFactory().getScheduler();
            scheduler2.start();
            scheduler2.scheduleJob(job2, trigger2);
            */
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
