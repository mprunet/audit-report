import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';
import { PrivilegesNecessairesService } from './privileges-necessaires.service';
import { PrivilegesNecessairesComponent } from './privileges-necessaires.component';
import { PrivilegesNecessairesDetailComponent } from './privileges-necessaires-detail.component';
import { PrivilegesNecessairesUpdateComponent } from './privileges-necessaires-update.component';
import { PrivilegesNecessairesDeletePopupComponent } from './privileges-necessaires-delete-dialog.component';
import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';

@Injectable({ providedIn: 'root' })
export class PrivilegesNecessairesResolve implements Resolve<IPrivilegesNecessaires> {
    constructor(private service: PrivilegesNecessairesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PrivilegesNecessaires> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PrivilegesNecessaires>) => response.ok),
                map((privilegesNecessaires: HttpResponse<PrivilegesNecessaires>) => privilegesNecessaires.body)
            );
        }
        return of(new PrivilegesNecessaires());
    }
}

export const privilegesNecessairesRoute: Routes = [
    {
        path: 'privileges-necessaires',
        component: PrivilegesNecessairesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PrivilegesNecessaires'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'privileges-necessaires/:id/view',
        component: PrivilegesNecessairesDetailComponent,
        resolve: {
            privilegesNecessaires: PrivilegesNecessairesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PrivilegesNecessaires'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'privileges-necessaires/new',
        component: PrivilegesNecessairesUpdateComponent,
        resolve: {
            privilegesNecessaires: PrivilegesNecessairesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PrivilegesNecessaires'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'privileges-necessaires/:id/edit',
        component: PrivilegesNecessairesUpdateComponent,
        resolve: {
            privilegesNecessaires: PrivilegesNecessairesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PrivilegesNecessaires'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const privilegesNecessairesPopupRoute: Routes = [
    {
        path: 'privileges-necessaires/:id/delete',
        component: PrivilegesNecessairesDeletePopupComponent,
        resolve: {
            privilegesNecessaires: PrivilegesNecessairesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PrivilegesNecessaires'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
