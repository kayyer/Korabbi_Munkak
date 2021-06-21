#ifndef FILMTAR_H_INCLUDED
#define FILMTAR_H_INCLUDED
#include <iostream>
#include "film.h"

class filmtar {

	int db;
	film** filmek;
	filmtar(const filmtar&);
public:
	filmtar();
	void add(film*);
	void del(film*);
	void del();
	film* keres(std::string);
	void beolvas(std::istream& is);
	void kiir();
	~filmtar();

};

#endif // FILMTAR_H_INCLUDED
