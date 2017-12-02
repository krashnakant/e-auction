import { BaseEntity } from './../../shared';

export class ItemAttribute implements BaseEntity {
    constructor(
        public id?: number,
        public value?: string,
        public attributeId?: number,
        public itemId?: number,
    ) {
    }
}
