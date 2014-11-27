import subprocess

database_parameters = ["mysql", "-h", "mysql.itu.dk", "--user=imdb_admin",
		"--password=password", "--skip-secure-auth", "imdb_project"]

def create_tables():
	subprocess.call(database_parameters, stdin=open("create_tables.sql", "r"))

def populate_tables():
	subprocess.call(database_parameters, stdin=open("import.sql", "r"))

create_tables()
populate_tables()