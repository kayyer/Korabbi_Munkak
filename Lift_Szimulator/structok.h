#ifndef STRUCTOK_H_INCLUDED
#define STRUCTOK_H_INCLUDED

typedef struct maxmin {

	int max_mennyit;
	int max_ki;
	int min_mennyit;
	int min_ki;

}maxmin;

typedef struct idok
{
	int ora;
	int perc;
	int mp;
} idok;


typedef struct utas
{
	int honnan;
	int hova;
	idok ido;
	int suly;
	int mpido;
	int id;
	struct utas* kov;
} utas;

typedef struct cel
{
	int ertek;
	int id;
	char mvf;
	struct cel* kov;
} cel;

typedef struct tulsok {

	int suly;
	int fo;

}tulsok;



#endif // STRUCTOK_H_INCLUDED


