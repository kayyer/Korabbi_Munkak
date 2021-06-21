#ifndef FILM_H_INCLUDED
#define FILM_H_INCLUDED
#include <iostream>

class film {
	std::string cim;
	double hossz;
	int megjelenes;
public:
	film(std::string, double, int);
	std::string getcim();
	virtual void kiir();
	virtual ~film();
	void set(std::string, double, int);

};

#endif // FILM_H_INCLUDED
