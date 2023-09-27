# spring-batch
Spring Batch is a framework within the broader Spring ecosystem that simplifies the development of batch processing applications. Batch processing involves the execution of a series of tasks, such as data processing, transformation, and loading, typically on large datasets, with minimal user interaction. Spring Batch provides a structured and scalable approach to building batch applications.

Here are some fundamental concepts and components of Spring Batch:

* Job: A Job represents a batch process to be executed. It consists of one or more Steps that are executed sequentially. A Job is defined using XML or Java configuration.

* Step: A Step is a single unit of work within a Job. It typically performs a specific task, such as reading data, processing it, and writing it to a destination. Steps can be chained together to form a sequence of processing.

* ItemReader: An ItemReader is responsible for reading data from a source, such as a database, file, or a message queue. Spring Batch provides various ItemReader implementations for different data sources.

* ItemProcessor: An ItemProcessor is used to process the data read by the ItemReader. It allows you to apply business logic, validation, transformation, or filtering to each item read from the source.

* ItemWriter: An ItemWriter is responsible for writing processed items to a destination, such as a database, file, or message queue. Spring Batch provides different ItemWriter implementations for various output formats.

* Chunk: A Chunk is a group of items processed together in a single transaction. The size of a chunk can be configured to optimize performance and manage transaction boundaries.

* JobRepository: The JobRepository is responsible for storing metadata about Job executions, including the status of each Job and Step. By default, Spring Batch uses a relational database to store this information.

* JobLauncher: The JobLauncher is responsible for starting Job executions. It is typically used to trigger batch processes programmatically.

* Listeners: Spring Batch provides various listener interfaces (JobExecutionListener, StepExecutionListener, ItemReadListener, ItemProcessListener, ItemWriteListener, etc.) that allow you to define custom logic to be executed before or after various batch processing events.

* Retry and Skip: Spring Batch provides mechanisms for handling errors during batch processing. You can configure retry logic to retry processing an item a certain number of times and define how to handle items that cannot be processed (skip or skip with retry).

* Partitioning: Spring Batch supports parallel processing by partitioning a Job into smaller sub-Jobs or threads, allowing you to process data concurrently. This is particularly useful for processing large datasets.

* Restartability: Spring Batch is designed to be restartable. It can identify the point of failure and resume processing from the last successfully processed item.

* To create a Spring Batch application, you typically define Jobs, Steps, ItemReaders, ItemProcessors, and ItemWriters in your application context or Java configuration. You can use annotations or XML configuration to specify the flow of your batch processing.

Spring Batch simplifies common batch processing challenges, such as handling large datasets, error handling, and transaction management. It is widely used in various industries for tasks like ETL (Extract, Transform, Load), report generation, and data synchronization.

I am running postgresdb locally with docker. The DockerFiile is here https://github.com/Kuldeep-Rana/spring-batch/blob/main/DockerFile. Run the following commands to run. 
```
 docker build -t my-postgres-image .
 docker run -d --name my-postgres-container -p 5432:5432 my-postgres-image
```

# Spring Batch relies on several database tables to manage the state and metadata of batch jobs and their steps. These tables help Spring Batch maintain information about job executions, job parameters, step executions, and more. Below, I'll explain the key tables used by Spring Batch one by one:
* BATCH_JOB_INSTANCE: This table stores information about each unique job that has been executed. A job instance is a logical job run and is identified by a unique JOB_INSTANCE_ID.

* BATCH_JOB_EXECUTION: This table maintains records of each execution of a job instance. Each execution of a job is identified by a unique JOB_EXECUTION_ID.

* BATCH_JOB_EXECUTION_PARAMS: This table stores the job parameters used for each job execution.

* BATCH_STEP_EXECUTION: This table stores information about each step execution within a job execution. Each step execution is identified by a unique STEP_EXECUTION_ID.

* BATCH_STEP_EXECUTION_CONTEXT: This table stores serialized context information associated with each step execution.

* BATCH_EXECUTION_EXECUTION_CONTEXT: This table stores serialized context information associated with each job execution.

 https://github.com/Kuldeep-Rana/spring-batch/blob/main/spring-batch-tables.PNG

# In this example https://github.com/Kuldeep-Rana/spring-batch/tree/main/spring-batch-2 I have shown how we can launch a job using Rest API, JobLauncher and Job. We can also supply the Job parameters using Rest API. One of the main points to remember here is to disable the auto batch job using following property.  

``` spring.batch.job.enabled=false ```

