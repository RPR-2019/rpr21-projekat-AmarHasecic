BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "CECMembers" (
	"code"	INTEGER,
	"username"	TEXT,
	"first_name"	TEXT,
	"last_name"	TEXT,
	PRIMARY KEY("code")
);
CREATE TABLE IF NOT EXISTS "Candidates" (
	"id"	INTEGER,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"numberOfVotes"	INTEGER,
	"political_party"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("political_party") REFERENCES "PoliticalParties"
);
CREATE TABLE IF NOT EXISTS "PoliticalParties" (
	"id"	INTEGER,
	"name"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Voters" (
	"id_pass"	TEXT,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"username"	TEXT,
	"jmbg"	TEXT,
	"date_of_birth"	TEXT,
	"city"	TEXT,
	"adress"	TEXT,
	"email"	TEXT,
	"phone"	TEXT,
	"badge"	TEXT,
	PRIMARY KEY("id_pass")
);
CREATE TABLE IF NOT EXISTS "voting_sheets" (
	"id"	INTEGER,
	"candidate"	TEXT,
	"political_party"	TEXT
);
INSERT INTO "PoliticalParties" VALUES (0,'No political party');
COMMIT;
