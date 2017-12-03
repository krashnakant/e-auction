import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { ItemAttribute } from './item-attribute.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ItemAttributeService {

    private resourceUrl = SERVER_API_URL + 'api/item-attributes';
    private listResourceUrl = SERVER_API_URL + 'api/item-attributes/item';

    constructor(private http: Http) { }

    create(itemAttribute: ItemAttribute): Observable<ItemAttribute> {
        const copy = this.convert(itemAttribute);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(itemAttribute: ItemAttribute): Observable<ItemAttribute> {
        const copy = this.convert(itemAttribute);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ItemAttribute> {
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

    list(id: number, req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(`${this.listResourceUrl}/${id}`, options)
            .map((res: Response) => this.convertResponse(res));
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
     * Convert a returned JSON object to ItemAttribute.
     */
    private convertItemFromServer(json: any): ItemAttribute {
        const entity: ItemAttribute = Object.assign(new ItemAttribute(), json);
        return entity;
    }

    /**
     * Convert a ItemAttribute to a JSON which can be sent to the server.
     */
    private convert(itemAttribute: ItemAttribute): ItemAttribute {
        const copy: ItemAttribute = Object.assign({}, itemAttribute);
        return copy;
    }
}
