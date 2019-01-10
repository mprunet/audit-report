/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { VulnerabiliteService } from 'app/entities/vulnerabilite/vulnerabilite.service';
import { IVulnerabilite, Vulnerabilite } from 'app/shared/model/vulnerabilite.model';

describe('Service Tests', () => {
    describe('Vulnerabilite Service', () => {
        let injector: TestBed;
        let service: VulnerabiliteService;
        let httpMock: HttpTestingController;
        let elemDefault: IVulnerabilite;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(VulnerabiliteService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Vulnerabilite(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Vulnerabilite', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Vulnerabilite(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Vulnerabilite', async () => {
                const returnedFromService = Object.assign(
                    {
                        ref: 'BBBBBB',
                        categorie: 'BBBBBB',
                        nom: 'BBBBBB',
                        description: 'BBBBBB',
                        probabilite: 1,
                        impact: 1,
                        criticite: 'BBBBBB',
                        cvss: 1,
                        recommandation: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Vulnerabilite', async () => {
                const returnedFromService = Object.assign(
                    {
                        ref: 'BBBBBB',
                        categorie: 'BBBBBB',
                        nom: 'BBBBBB',
                        description: 'BBBBBB',
                        probabilite: 1,
                        impact: 1,
                        criticite: 'BBBBBB',
                        cvss: 1,
                        recommandation: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Vulnerabilite', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
