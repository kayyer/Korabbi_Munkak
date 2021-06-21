#ifndef DOKU_H_INCLUDED
#define DOKU_H_INCLUDED
#include <iostream>
#include "film.h"

class doku :public film {

	std::string leiras;
public:
	doku(std::string, double, int, std::string);
	void kiir();
	~doku();
};

#endif // DOKU_H_INCLUDED
