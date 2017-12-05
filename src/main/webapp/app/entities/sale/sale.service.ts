import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Sale } from './sale.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SaleService {

    private resourceUrl = SERVER_API_URL + 'api/sales';
    private listResourceUrl = SERVER_API_URL + 'api/sales/category';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(sale: Sale): Observable<Sale> {
        const copy = this.convert(sale);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(sale: Sale): Observable<Sale> {
        const copy = this.convert(sale);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Sale> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    list(id: number): Observable<Sale[]> {
        return this.http.get(`${this.listResourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Sale.
     */
    private convertItemFromServer(json: any): Sale {
        const entity: Sale = Object.assign(new Sale(), json);
        entity.start = this.dateUtils
            .convertLocalDateFromServer(json.start);
        entity.end = this.dateUtils
            .convertLocalDateFromServer(json.end);
        return entity;
    }

    /**
     * Convert a Sale to a JSON which can be sent to the server.
     */
    private convert(sale: Sale): Sale {
        const copy: Sale = Object.assign({}, sale);
        copy.start = this.dateUtils
            .convertLocalDateToServer(sale.start);
        copy.end = this.dateUtils
            .convertLocalDateToServer(sale.end);
        return copy;
    }
}
