import { Component, OnInit, Input } from '@angular/core';

import { Item } from '../item.model';
import { ItemService } from '../item.service';

@Component({
    selector: 'app-item-label',
    templateUrl: './item-label.component.html'
})
export class ItemLabelComponent implements OnInit {
    @Input()
    id: number;
    item: Item;

    constructor(
        private itemService: ItemService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.itemService.find(id).subscribe((item) => {
            this.item = item;
        });
    }
    previousState() {
        window.history.back();
    }
}
