import re
import codecs

# Pattern for the different tables
pattern = re.compile("LOCK TABLES `([\w]+)` WRITE;")

# Loop through every line in the input file
outputfile = 0
importfile = codecs.open("import.sql", "w+", encoding="utf-8");
with codecs.open("imdb-r.txt", "r", encoding="utf-8") as datafile:
	for line in datafile:
		if pattern.match(line):
			# If the pattern matches, it is a new database table

			filename = pattern.match(line).group(1)
			outputfile = codecs.open(filename + ".txt", "w+", encoding="utf-8")
			importfile.write("LOAD DATA LOCAL INFILE '" + filename
							+ ".txt' INTO TABLE " + filename + ";\n")
		else:
			outputfile.write(line)

