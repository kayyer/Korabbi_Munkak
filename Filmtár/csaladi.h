#ifndef CSALADI_H_INCLUDED
#define CSALADI_H_INCLUDED
#include <iostream>
#include "film.h"

class csaladi :public film {
	int korhatar;
public:
	csaladi(std::string, double, int, int);
	void kiir();
	~csaladi();
};

#endif // CSALADI_H_INCLUDED
