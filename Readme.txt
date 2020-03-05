Spring Batch Consumer App:

	This app is getting Students data with by using RestTemplate by calling a Producer app.

	Then Batch Launcher will launch batch processing and do the following tasks.

	1) read data from Provider app's API.

	2) process data and generate result.

	3) write data to result_master.

	4) again read data.

	5) store it to student_master.