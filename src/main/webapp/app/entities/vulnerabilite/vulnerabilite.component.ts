import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';
import { AccountService } from 'app/core';
import { VulnerabiliteService } from './vulnerabilite.service';

@Component({
    selector: 'jhi-vulnerabilite',
    templateUrl: './vulnerabilite.component.html'
})
export class VulnerabiliteComponent implements OnInit, OnDestroy {
    vulnerabilites: IVulnerabilite[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected vulnerabiliteService: VulnerabiliteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.vulnerabiliteService.query().subscribe(
            (res: HttpResponse<IVulnerabilite[]>) => {
                this.vulnerabilites = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVulnerabilites();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVulnerabilite) {
        return item.id;
    }

    registerChangeInVulnerabilites() {
        this.eventSubscriber = this.eventManager.subscribe('vulnerabiliteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
