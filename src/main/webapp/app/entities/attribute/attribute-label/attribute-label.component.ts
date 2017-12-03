import { Component, OnInit, Input } from '@angular/core';

import { Attribute } from '../attribute.model';
import { AttributeService } from '../attribute.service';

@Component({
    selector: 'app-attribute-label',
    templateUrl: './attribute-label.component.html'
})
export class AttributeLabelComponent implements OnInit {
    @Input()
    id: number;
    attribute: Attribute;

    constructor(
        private attributeService: AttributeService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.attributeService.find(id).subscribe((attribute) => {
            this.attribute = attribute;
        });
    }
    previousState() {
        window.history.back();
    }
}
