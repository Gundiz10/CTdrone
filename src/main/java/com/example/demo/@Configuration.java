@Configuration
public class QuartzConfig {
    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactoryBean.setTriggers(batteryCheckTrigger().getObject());
        return schedulerFactoryBean;
    }

    @Bean
    public JobDetailFactoryBean batteryCheckJob() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(BatteryCheckJob.class);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean batteryCheckTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(batteryCheckJob().getObject());
        cronTriggerFactoryBean.setCronExpression("0 0/10 * * * ?"); // every 10 minutes
        return cronTriggerFactoryBean;
    }
}