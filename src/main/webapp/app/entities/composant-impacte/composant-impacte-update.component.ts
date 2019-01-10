import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';
import { ComposantImpacteService } from './composant-impacte.service';

@Component({
    selector: 'jhi-composant-impacte-update',
    templateUrl: './composant-impacte-update.component.html'
})
export class ComposantImpacteUpdateComponent implements OnInit {
    composantImpacte: IComposantImpacte;
    isSaving: boolean;

    constructor(protected composantImpacteService: ComposantImpacteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ composantImpacte }) => {
            this.composantImpacte = composantImpacte;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.composantImpacte.id !== undefined) {
            this.subscribeToSaveResponse(this.composantImpacteService.update(this.composantImpacte));
        } else {
            this.subscribeToSaveResponse(this.composantImpacteService.create(this.composantImpacte));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IComposantImpacte>>) {
        result.subscribe((res: HttpResponse<IComposantImpacte>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
