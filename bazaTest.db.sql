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
INSERT INTO "CECMembers" VALUES (123,'cec','cec_first_name','cec_last_name');
INSERT INTO "Candidates" VALUES (0,'Candidate1_FN','Candidate1_LN',0,1);
INSERT INTO "Candidates" VALUES (1,'Candidate2_FN','Candidate2_LN',0,2);
INSERT INTO "Candidates" VALUES (2,'Candidate3_FN','Candidate3_LN',0,1);
INSERT INTO "Candidates" VALUES (3,'Candidate4_FN','Candidate4_LN',0,1);
INSERT INTO "Candidates" VALUES (4,'Candidate5_FN','Candidate5_LN',0,1);
INSERT INTO "Candidates" VALUES (5,'Candidate6_FN','Candidate6_LN',0,1);
INSERT INTO "PoliticalParties" VALUES (1,'Stranka A');
INSERT INTO "PoliticalParties" VALUES (2,'Stranka B');
INSERT INTO "Voters" VALUES ('voter','voter_FN','VOTER_LN','voter','123456','1.1.2000','Sarajevo','ETF','voter1@etf.unsa.ba','061 111 111',NULL);
COMMIT;
