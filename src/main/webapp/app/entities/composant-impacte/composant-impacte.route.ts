import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComposantImpacte } from 'app/shared/model/composant-impacte.model';
import { ComposantImpacteService } from './composant-impacte.service';
import { ComposantImpacteComponent } from './composant-impacte.component';
import { ComposantImpacteDetailComponent } from './composant-impacte-detail.component';
import { ComposantImpacteUpdateComponent } from './composant-impacte-update.component';
import { ComposantImpacteDeletePopupComponent } from './composant-impacte-delete-dialog.component';
import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';

@Injectable({ providedIn: 'root' })
export class ComposantImpacteResolve implements Resolve<IComposantImpacte> {
    constructor(private service: ComposantImpacteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ComposantImpacte> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComposantImpacte>) => response.ok),
                map((composantImpacte: HttpResponse<ComposantImpacte>) => composantImpacte.body)
            );
        }
        return of(new ComposantImpacte());
    }
}

export const composantImpacteRoute: Routes = [
    {
        path: 'composant-impacte',
        component: ComposantImpacteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComposantImpactes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-impacte/:id/view',
        component: ComposantImpacteDetailComponent,
        resolve: {
            composantImpacte: ComposantImpacteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComposantImpactes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-impacte/new',
        component: ComposantImpacteUpdateComponent,
        resolve: {
            composantImpacte: ComposantImpacteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComposantImpactes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'composant-impacte/:id/edit',
        component: ComposantImpacteUpdateComponent,
        resolve: {
            composantImpacte: ComposantImpacteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComposantImpactes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const composantImpactePopupRoute: Routes = [
    {
        path: 'composant-impacte/:id/delete',
        component: ComposantImpacteDeletePopupComponent,
        resolve: {
            composantImpacte: ComposantImpacteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComposantImpactes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
