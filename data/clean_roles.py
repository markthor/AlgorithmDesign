import codecs
import re

def include(role):
	if role == "" or role.startswith("himself") or role.startswith("Himself") or 
		role.startswith("themselves") or role.startswith("Themselves") or
		role.startswith("herself") or role.startswith("Herself") or
		role.startswith("performer") or role.startswith("Performer") or
		role.startswith("additional") or role.startswith("Additional"):
		return False
	else:
		return True

def clean(string):
	string = re.sub("#", "", string)
	string = re.sub("\([\d]+\)", "", string)
	string = re.sub("\([\d]+-[\d]+\)", "", string)
	string = re.sub("[\d]+.", "", string)
	string = re.sub("[\d]+", "", string)
	string = re.sub("\(", "", string)
	string = re.sub("\)", "", string)
	string = re.sub("No\.?", "", string)
	string = re.sub("no\.?", "", string)
	string = re.sub("\\\\", "", string)
	string = re.sub("/", "", string)
	return string.strip().strip(".").strip("-").strip() + "\n"

def initial_cleaning():
	with codecs.open("data_files/roles.txt", "r", encoding="utf-8") as datafile:
		with codecs.open("data_files/roles_cleaned.txt", "w+", encoding="utf-8") as outputfile:
			with codecs.open("data_files/roles_discarded.txt", "w+", encoding="utf-8") as discardfile:
				for line in datafile:
					#Remove first number plus the first comma
					role = line[line.find(",")+1:]
					#Remove second number plus the second comma and the first '
					role = role[role.find(",")+2:]
					#Remove last character and EOF
					role = role[:-2]
					if include(role):
						outputfile.write(clean(role))
					else:
						discardfile.write(role+"\n")	

initial_cleaning()