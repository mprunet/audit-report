import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVulnerabiliteRef } from 'app/shared/model/vulnerabilite-ref.model';
import { AccountService } from 'app/core';
import { VulnerabiliteRefService } from './vulnerabilite-ref.service';

@Component({
    selector: 'jhi-vulnerabilite-ref',
    templateUrl: './vulnerabilite-ref.component.html'
})
export class VulnerabiliteRefComponent implements OnInit, OnDestroy {
    vulnerabiliteRefs: IVulnerabiliteRef[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected vulnerabiliteRefService: VulnerabiliteRefService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.vulnerabiliteRefService.query().subscribe(
            (res: HttpResponse<IVulnerabiliteRef[]>) => {
                this.vulnerabiliteRefs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVulnerabiliteRefs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVulnerabiliteRef) {
        return item.id;
    }

    registerChangeInVulnerabiliteRefs() {
        this.eventSubscriber = this.eventManager.subscribe('vulnerabiliteRefListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
