#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include "structok.h"
#include "bekiolvas.h"
#include "utasmod.h"
#include "celmod.h"







bool vanhely(utas li[], int xtra,tulsok tult)
{
    int ossz = 0;
    int letszam = 0;
    utas *uj;
    for (uj = li; uj != NULL; uj = uj->kov)
    {
        ossz += uj->suly;
        letszam++;
    }
    ossz += xtra;
    if (ossz<tult.suly && letszam < tult.fo)
    {
        return true;
    }

    return false;
}




int mpidosz(int o, int p, int m)

{
    int j;
    j = (o * 3600) + (p * 60) + m;
    return j;
}


void beszall(utas *varolista[], utas *liftenvan[], int liftemelet, idok ido, cel** irany, tulsok tul, int *varatl, maxmin *max,int *varomod)
{
    utas *ujvar;

    int cnt = 0;
    ujvar = *varolista;

    while (ujvar != NULL)
    {
        if (liftemelet == ujvar->honnan)
        {

            if (vanhely(*liftenvan, ujvar->suly,tul))
            {
                int xeo = mpidosz(ido.ora, ido.perc, ido.mp);
                *varatl += (xeo - ujvar->mpido);

                if ( *varomod  || (xeo - ujvar->mpido) > (*max).max_mennyit)
                {

                    (*max).max_ki = ujvar->id;
                    (*max).max_mennyit = (xeo - ujvar->mpido);

                }

                if (*varomod  || (xeo - ujvar->mpido) < (*max).min_mennyit)
                {
                    (*max).min_ki = ujvar->id;
                    (*max).min_mennyit = (xeo - ujvar->mpido);
                    *varomod = 0;
                }

                *liftenvan = befuz(*liftenvan, *ujvar);

                *irany = celbefuz(*irany, *ujvar, ujvar->hova, 'f');
                *irany = celtorol(*irany, ujvar->id, 'm');


                cnt++;

                printf("%.2d:%.2d:%.2d  : Beszall a %d-es ember %d.emeleten (a %d emeletre tart)\n", ido.ora,ido.perc,ido.mp, ujvar->id, liftemelet,ujvar->hova);
                ujvar = ujvar->kov;
                *varolista = torol(*varolista, liftemelet);


            }
            else
            {


                if (((*irany)->ertek == liftemelet) && ((*irany)->mvf == 'm'))
                {
                    cel *elsoliftenlevo = *irany;
                    while (elsoliftenlevo->mvf != 'f')
                        elsoliftenlevo = elsoliftenlevo->kov;

                    elemcsere(irany, *elsoliftenlevo);


                }
                printf("NINCS HELY A LIFTEN\n");
                ujvar = ujvar->kov;
            }

        }
        else
        {
            ujvar = ujvar->kov;
        }
    }

}


void leszall(int liftemelet, utas **liftenvan, idok ido, cel **irany, int* vege, int vegid)
{

    utas* iwoe = *liftenvan;

    int os = 0;
    while (iwoe != NULL)
    {
        if (iwoe->hova == liftemelet)
        {
            os++;
            *irany = celtorol(*irany, iwoe->id, 'f');

            if (iwoe->id == vegid)
                *vege = 1;

            printf("%.2d:%.2d:%.2d  : Kiszall a %d-es ember %d emeleten\n", ido.ora,ido.perc,ido.mp, iwoe->id, liftemelet);
            iwoe = iwoe->kov;

            *liftenvan = torolliftrol(*liftenvan, liftemelet);
        }
        else
        {
            iwoe = iwoe->kov;
        }
    }


}



int rendez(const void *a, const void *b)
{
    utas *p1 = (utas*)a;
    utas *p2 = (utas*)b;

    return (((p1->ido.ora) * 3600) + ((p1->ido.perc) * 60) + p1->ido.mp) - (((p2->ido.ora) * 3600) + ((p2->ido.perc) * 60) + p2->ido.mp);
}





void statisztika(int varatl,int osszemelet, int meret,const utas* lista,maxmin ia)
{

    int orak[24] = { 0 };
    int pazarloossz=0;
    int szaml;
    double tav;
    double varakozasiatl;
    double szaz;
    for(szaml=0; szaml<meret; szaml++)
    {
        tav = sqrt(lista[szaml].hova * lista[szaml].hova) + (lista[szaml].honnan * lista[szaml].honnan);
        pazarloossz += (lista[szaml].honnan + (int)tav + lista[szaml].hova);
        orak[lista[szaml].ido.ora] += 1;

    }

    varakozasiatl = varatl / meret;
    szaz =((double)osszemelet )/ pazarloossz;
    pazarloossz-=osszemelet;

    kiir(varakozasiatl, orak, pazarloossz,szaz, ia);

}



void liftmegy(utas *tomb, int meret, tulsok tult)  //ezt csak rendezes utan szabad hasznalni
{
    int mp;
    int liftemelet = 0;

    utas *varolista = NULL;
    utas *liftenvan = NULL;
    cel* irany = NULL;

    bool liftemeletvaltozas = false;

    int i = 0;
    int vege = 0;
    int mennyi = 0;
    int vegig = 0;

    idok mpkonv;
    int celkiir=0;

    int varatl = 0;
    int osszemelet = 0;
    maxmin axin;
    int varomod = 1;

    for (mp = ( tomb[0].mpido - (tomb[0].mpido % 10)); vege != 1; mp++)
    {
        mpkonv.ora = mp / 3600;
        mpkonv.perc = (mp - (mpkonv.ora*3600)) / 60;
        mpkonv.mp = (mp - (mpkonv.ora*3600)) - (mpkonv.perc*60);
        if (mp == tomb[i].mpido)
        {
            utas *egyszerre = (utas*)malloc(meret * sizeof(utas));
            while (mp == tomb[i].mpido)
            {
                egyszerre[mennyi] = tomb[i];
                i++;
                mennyi++;
            }
            if (mennyi>1)
                egyszerre = tobb_nyom_rendez(egyszerre, mennyi, liftemelet);
            while (vegig<mennyi)
            {

                printf("%.2d:%.2d:%.2d  : Megnyomta a gombot a %d-es ember a %d. emeleten\n", egyszerre[vegig].ido.ora, egyszerre[vegig].ido.perc, egyszerre[vegig].ido.mp, egyszerre[vegig].id, egyszerre[vegig].honnan);
                varolista = befuz(varolista, egyszerre[vegig]);
                if (irany != NULL)
                    celkiir = irany->ertek;
                irany = celbefuz(irany, egyszerre[vegig], egyszerre[vegig].honnan, 'm');
                if (irany->ertek != celkiir)
                    printf( "\t\t\t |uj cel : %d|\n",irany->ertek );

                vegig++;
            }
            mennyi = 0;
            vegig = 0;
            free(egyszerre);
        }
        if ((irany == NULL || (liftemelet> irany->ertek)) && mp % 10 == 0 && !liftemeletvaltozas)
        {
            if(liftemelet>0)
                liftemelet--;
            osszemelet++;

            printf("%.2d:%.2d:%.2d  : %d\n",mpkonv.ora,mpkonv.perc,mpkonv.mp, liftemelet);
            if (irany != NULL)
                celkiir = irany->ertek;

            leszall(liftemelet, &liftenvan, mpkonv, &irany, &vege, tomb[meret - 1].id);
            beszall(&varolista, &liftenvan, liftemelet, mpkonv, &irany, tult,&varatl,&axin,&varomod);

            if (irany!=NULL && irany->ertek != celkiir)
                printf("\t\t\t |uj cel: %d|\n", irany->ertek);
            liftemeletvaltozas = true;

        }

        if ((irany!=NULL) && ( (liftemelet < irany->ertek) && (mp % 10 == 0) && !liftemeletvaltozas))
        {

            liftemelet++;
            osszemelet++;
            printf("%.2d:%.2d:%.2d  : %d\n", mpkonv.ora, mpkonv.perc, mpkonv.mp, liftemelet);

            if (irany != NULL)
                celkiir = irany->ertek;

            leszall(liftemelet, &liftenvan, mpkonv, &irany, &vege, tomb[meret - 1].id);

            beszall(&varolista, &liftenvan, liftemelet, mpkonv, &irany, tult,&varatl,&axin,&varomod);
            liftemeletvaltozas = true;

            if (irany !=NULL && irany->ertek != celkiir)
                printf("\t\t\t |uj cel: %d|\n", irany->ertek);
        }

        liftemeletvaltozas = false;
    }
    statisztika(varatl,osszemelet,meret,tomb,axin);
    free(varolista);
    free(liftenvan);
}




int main()
{


    utas *utasok;
    int cnt = 0;
    tulsok tult;


    utasok = beolvas(&cnt,&tult);
    if (tult.suly <=0 || tult.fo <= 0)
        return 1;
    qsort(utasok, cnt, sizeof(utas), rendez);
    for (int j = 0; j<cnt; j++)
        utasok[j].mpido = mpidosz(utasok[j].ido.ora, utasok[j].ido.perc, utasok[j].ido.mp);


    if (cnt == 0)
        printf("Nincs sikeresen beolvasott adat");
    else
    {
        liftmegy(utasok, cnt,tult);
    }
    free(utasok);
    return 0;
}

