import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVulnerabilite } from 'app/shared/model/vulnerabilite.model';
import { VulnerabiliteService } from './vulnerabilite.service';
import { IProjet } from 'app/shared/model/projet.model';
import { ProjetService } from 'app/entities/projet';
import { IPrivilegesNecessaires } from 'app/shared/model/privileges-necessaires.model';
import { PrivilegesNecessairesService } from 'app/entities/privileges-necessaires';
import { IComposantImpacte } from 'app/shared/model/composant-impacte.model';
import { ComposantImpacteService } from 'app/entities/composant-impacte';

@Component({
    selector: 'jhi-vulnerabilite-update',
    templateUrl: './vulnerabilite-update.component.html'
})
export class VulnerabiliteUpdateComponent implements OnInit {
    vulnerabilite: IVulnerabilite;
    isSaving: boolean;

    projets: IProjet[];

    privilegesnecessaires: IPrivilegesNecessaires[];

    composantimpactes: IComposantImpacte[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected vulnerabiliteService: VulnerabiliteService,
        protected projetService: ProjetService,
        protected privilegesNecessairesService: PrivilegesNecessairesService,
        protected composantImpacteService: ComposantImpacteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vulnerabilite }) => {
            this.vulnerabilite = vulnerabilite;
        });
        this.projetService.query().subscribe(
            (res: HttpResponse<IProjet[]>) => {
                this.projets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.privilegesNecessairesService.query().subscribe(
            (res: HttpResponse<IPrivilegesNecessaires[]>) => {
                this.privilegesnecessaires = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.composantImpacteService.query().subscribe(
            (res: HttpResponse<IComposantImpacte[]>) => {
                this.composantimpactes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vulnerabilite.id !== undefined) {
            this.subscribeToSaveResponse(this.vulnerabiliteService.update(this.vulnerabilite));
        } else {
            this.subscribeToSaveResponse(this.vulnerabiliteService.create(this.vulnerabilite));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IVulnerabilite>>) {
        result.subscribe((res: HttpResponse<IVulnerabilite>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProjetById(index: number, item: IProjet) {
        return item.id;
    }

    trackPrivilegesNecessairesById(index: number, item: IPrivilegesNecessaires) {
        return item.id;
    }

    trackComposantImpacteById(index: number, item: IComposantImpacte) {
        return item.id;
    }
}
