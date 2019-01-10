import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';
import { VulnerabiliteRefService } from './vulnerabilite-ref.service';
import { VulnerabiliteRefComponent } from './vulnerabilite-ref.component';
import { VulnerabiliteRefDetailComponent } from './vulnerabilite-ref-detail.component';
import { VulnerabiliteRefUpdateComponent } from './vulnerabilite-ref-update.component';
import { VulnerabiliteRefDeletePopupComponent } from './vulnerabilite-ref-delete-dialog.component';
import { IVulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';

@Injectable({ providedIn: 'root' })
export class VulnerabiliteRefResolve implements Resolve<IVulnerabiliteRef> {
    constructor(private service: VulnerabiliteRefService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VulnerabiliteRef> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VulnerabiliteRef>) => response.ok),
                map((vulnerabiliteRef: HttpResponse<VulnerabiliteRef>) => vulnerabiliteRef.body)
            );
        }
        return of(new VulnerabiliteRef());
    }
}

export const vulnerabiliteRefRoute: Routes = [
    {
        path: 'vulnerabilite-ref',
        component: VulnerabiliteRefComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VulnerabiliteRefs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vulnerabilite-ref/:id/view',
        component: VulnerabiliteRefDetailComponent,
        resolve: {
            vulnerabiliteRef: VulnerabiliteRefResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VulnerabiliteRefs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vulnerabilite-ref/new',
        component: VulnerabiliteRefUpdateComponent,
        resolve: {
            vulnerabiliteRef: VulnerabiliteRefResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VulnerabiliteRefs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vulnerabilite-ref/:id/edit',
        component: VulnerabiliteRefUpdateComponent,
        resolve: {
            vulnerabiliteRef: VulnerabiliteRefResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VulnerabiliteRefs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vulnerabiliteRefPopupRoute: Routes = [
    {
        path: 'vulnerabilite-ref/:id/delete',
        component: VulnerabiliteRefDeletePopupComponent,
        resolve: {
            vulnerabiliteRef: VulnerabiliteRefResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'VulnerabiliteRefs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
