In this example I have demonstrated how we can use Spring batch with chunk step processing
to read a CSV file and insert that data into the database table. 

### Here how the processing happens
* Have CSV file with input data available at [users.csv](input/users.csv)
* Configure a chunk oriented job with chunk size 100, or you can give any value
* Have Item reader to read from the file
* Have Item writer to write into db, I am using jdbc template to batch insert into db 
