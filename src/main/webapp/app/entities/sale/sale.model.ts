import { BaseEntity } from './../../shared';

export class Sale implements BaseEntity {
    constructor(
        public id?: number,
        public auctionTitle?: string,
        public start?: any,
        public end?: any,
        public items?: BaseEntity[],
        public categoryId?: number,
    ) {
    }
}
