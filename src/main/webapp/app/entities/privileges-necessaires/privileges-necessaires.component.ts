import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';
import { AccountService } from 'app/core';
import { PrivilegesNecessairesService } from './privileges-necessaires.service';

@Component({
    selector: 'jhi-privileges-necessaires',
    templateUrl: './privileges-necessaires.component.html'
})
export class PrivilegesNecessairesComponent implements OnInit, OnDestroy {
    privilegesNecessaires: IPrivilegesNecessaires[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected privilegesNecessairesService: PrivilegesNecessairesService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.privilegesNecessairesService.query().subscribe(
            (res: HttpResponse<IPrivilegesNecessaires[]>) => {
                this.privilegesNecessaires = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPrivilegesNecessaires();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPrivilegesNecessaires) {
        return item.id;
    }

    registerChangeInPrivilegesNecessaires() {
        this.eventSubscriber = this.eventManager.subscribe('privilegesNecessairesListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
