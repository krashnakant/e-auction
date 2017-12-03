import { Component, OnInit, Input } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';

import { ItemAttribute } from '../item-attribute.model';
import { ItemAttributeService } from '../item-attribute.service';
import { ResponseWrapper } from '../../../shared';

@Component({
    selector: 'app-item-attribute-list',
    templateUrl: './item-attribute-list.component.html'
})
export class ItemAttributeListComponent implements OnInit {

    @Input()
    itemId: number;
    itemAttributes: ItemAttribute[];

    constructor(
        private itemAttributeService: ItemAttributeService,
        private jhiAlertService: JhiAlertService
    ) { }

    loadAll(id: number) {
        this.itemAttributeService.list(id).subscribe(
            (res: ResponseWrapper) => {
                this.itemAttributes = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll(this.itemId);
    }

    trackId(index: number, item: ItemAttribute) {
        return item.id;
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
