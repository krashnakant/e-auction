import { Component, OnInit, Input } from '@angular/core';

import { ItemAttribute } from '../item-attribute.model';
import { ItemAttributeService } from '../item-attribute.service';

@Component({
    selector: 'app-item-attribute-label',
    templateUrl: './item-attribute-label.component.html'
})
export class ItemAttributeLabelComponent implements OnInit {
    @Input()
    id: number;
    itemAttribute: ItemAttribute;

    constructor(
        private itemAttributeService: ItemAttributeService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.itemAttributeService.find(id).subscribe((itemAttribute) => {
            this.itemAttribute = itemAttribute;
        });
    }
    previousState() {
        window.history.back();
    }
}
