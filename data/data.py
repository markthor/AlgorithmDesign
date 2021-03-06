import re
import codecs

# Pattern for the different tables
pattern = re.compile("LOCK TABLES `([\w]+)` WRITE;")

# Loop through every line in the input file
outputfile = None
importfile = codecs.open("data_files/import.sql", "w+", encoding="utf-8");
with codecs.open("data_files/imdb-r.txt", "r", encoding="utf-8") as datafile:
	for line in datafile:
		if pattern.match(line):
			# If the pattern matches, it is a new database table

			filename = pattern.match(line).group(1)
			outputfile = codecs.open("data_files/" + filename + ".txt", "w+", encoding="utf-8")
			importfile.write("LOAD DATA LOCAL INFILE '" + filename
							+ ".txt' INTO TABLE " + filename + ";\n")
		else:
			outputfile.write(line)

