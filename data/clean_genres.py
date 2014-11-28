import codecs

with codecs.open("data_files/movies_genres.txt", "r", encoding="utf-8") as datafile:
	with codecs.open("data_files/genres.txt", "w+", encoding="utf-8") as outputfile:
		for line in datafile:
			genre = line.split(",")[1].replace("'", "")
			if genre != "Unknown\n":
				outputfile.write(genre)