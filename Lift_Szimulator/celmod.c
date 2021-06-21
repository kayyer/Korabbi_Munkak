#include <stdio.h>
#include <stdlib.h>
#include "celmod.h"



cel* elozoelem(cel* elso, cel* elem)
{
    if (elso == elem)
        return NULL;
    cel* tmp = elso;
    cel* elozo = NULL;
    while (tmp && tmp != elem)
    {
        elozo = tmp;
        tmp = tmp->kov;
    }
    if (tmp != elem)
    {
        printf("Rossz elemet adtal meg");
        exit(EXIT_FAILURE) ;
    }
    return elozo;

}

cel* celtorol(cel* p, int mitid, char c)
{
    if (p == NULL)
    {
        return NULL;
    }
    if (p->id == mitid && p->mvf == c)
    {
        cel* tmp = p->kov;
        free(p);
        return tmp;
    }
    p->kov = celtorol(p->kov, mitid, c);
    return p;
}

cel* celbefuz(cel* elso, utas a, int ce, char c)
{
    cel *uj = (cel *)malloc(sizeof(cel));
    uj->ertek = ce;
    uj->id = a.id;
    uj->mvf = c;
    uj->kov = NULL;

    if (elso == NULL)
    {
        elso = uj;
    }
    else
    {
        cel* tmp;
        for (tmp = elso; tmp->kov != NULL; tmp = tmp->kov);


        tmp->kov = uj;
    }

    return elso;


}

void elemcsere(cel **elso, cel mit)
{
    if (elso == NULL)
        return;
    {
        cel *gyoker = *elso;
        cel *vegig = gyoker;
        cel *elozo = NULL;
        int count = 0;
        while (vegig != NULL && vegig->id != mit.id && vegig->mvf != 'f')
        {
            elozo = vegig;
            vegig = vegig->kov;
            count++;
        }
        if (vegig == NULL)
            return;

        if (count == 0)
        {
            *elso = gyoker->kov;
            gyoker = *elso;
        }
        else
        {
            if(elozo != NULL)
                elozo->kov = vegig->kov;
        }


        vegig->kov = gyoker;
        *elso = vegig;
        return;


    }
}


