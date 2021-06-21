#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "utasmod.h"





void bemasol(utas *p, utas a)
{

    p->id = a.id;
    p->honnan = a.honnan;
    p->hova = a.hova;
    p->mpido = a.mpido;
    p->ido = a.ido;
    p->suly = a.suly;

}

utas* torol(utas* p, int mit)
{
    if (p == NULL)
        return NULL;
    if (p->honnan == mit)
    {

        utas *tmp = p->kov;
        free(p);

        return tmp;
    }
    p->kov = torol(p->kov, mit);
    return p;
}

utas* torolliftrol(utas* p, int mit)
{
    if (p == NULL)
        return NULL;
    if (p->hova == mit)
    {
        utas *tmp = p->kov;
        free(p);
        return tmp;
    }
    p->kov = torolliftrol(p->kov, mit);
    return p;
}

utas *befuz(utas *head, utas a)
{
    utas *uj = (utas *)malloc(sizeof(utas));
    bemasol(uj, a);
    uj->kov = NULL;

    if (head == NULL)
    {
        head = uj;
    }
    else
    {
        utas* tmp;
        for (tmp = head; tmp->kov != NULL; tmp = tmp->kov);


        tmp->kov = uj;
    }

    return head;
}

utas* tobb_nyom_rendez(utas tomb[], int meret, int liftemelet)
{
    int i, j;
    for (j = 0; j<meret - 1; j++)
    {
        for (i = j + 1; i<meret; i++)
        {
            double x = sqrt((tomb[j].honnan*tomb[j].honnan) + (liftemelet*liftemelet));
            double y = sqrt((tomb[i].honnan*tomb[i].honnan) + (liftemelet*liftemelet));

            if (x>y)
            {
                utas tmp = tomb[i];
                tomb[i] = tomb[j];
                tomb[j] = tmp;
            }
            if (x == y)
            {
                if (tomb[j].honnan > tomb[i].honnan)
                {
                    utas tmp = tomb[i];
                    tomb[i] = tomb[j];
                    tomb[j] = tmp;
                }
            }

        }

    }
    return tomb;
}

