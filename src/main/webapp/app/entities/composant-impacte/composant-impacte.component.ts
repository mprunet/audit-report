import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';
import { AccountService } from 'app/core';
import { ComposantImpacteService } from './composant-impacte.service';

@Component({
    selector: 'jhi-composant-impacte',
    templateUrl: './composant-impacte.component.html'
})
export class ComposantImpacteComponent implements OnInit, OnDestroy {
    composantImpactes: IComposantImpacte[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected composantImpacteService: ComposantImpacteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.composantImpacteService.query().subscribe(
            (res: HttpResponse<IComposantImpacte[]>) => {
                this.composantImpactes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInComposantImpactes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IComposantImpacte) {
        return item.id;
    }

    registerChangeInComposantImpactes() {
        this.eventSubscriber = this.eventManager.subscribe('composantImpacteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
