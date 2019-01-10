import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Vulnerabilite } from 'app/shared/model/vulnerabilite.model';
import { VulnerabiliteService } from './vulnerabilite.service';
import { VulnerabiliteComponent } from './vulnerabilite.component';
import { VulnerabiliteDetailComponent } from './vulnerabilite-detail.component';
import { VulnerabiliteUpdateComponent } from './vulnerabilite-update.component';
import { VulnerabiliteDeletePopupComponent } from './vulnerabilite-delete-dialog.component';
import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';

@Injectable({ providedIn: 'root' })
export class VulnerabiliteResolve implements Resolve<IVulnerabilite> {
    constructor(private service: VulnerabiliteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Vulnerabilite> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Vulnerabilite>) => response.ok),
                map((vulnerabilite: HttpResponse<Vulnerabilite>) => vulnerabilite.body)
            );
        }
        return of(new Vulnerabilite());
    }
}

export const vulnerabiliteRoute: Routes = [
    {
        path: 'vulnerabilite',
        component: VulnerabiliteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vulnerabilites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vulnerabilite/:id/view',
        component: VulnerabiliteDetailComponent,
        resolve: {
            vulnerabilite: VulnerabiliteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vulnerabilites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vulnerabilite/new',
        component: VulnerabiliteUpdateComponent,
        resolve: {
            vulnerabilite: VulnerabiliteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vulnerabilites'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vulnerabilite/:id/edit',
        component: VulnerabiliteUpdateComponent,
        resolve: {
            vulnerabilite: VulnerabiliteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vulnerabilites'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vulnerabilitePopupRoute: Routes = [
    {
        path: 'vulnerabilite/:id/delete',
        component: VulnerabiliteDeletePopupComponent,
        resolve: {
            vulnerabilite: VulnerabiliteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Vulnerabilites'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
