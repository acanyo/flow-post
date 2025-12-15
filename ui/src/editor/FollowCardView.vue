<script lang="ts" setup>
import type {NodeViewProps} from "@halo-dev/richtext-editor";
import { NodeViewWrapper } from "@halo-dev/richtext-editor";
import type { FollowCard } from '@kunkunyu/follow-card';
import {computed} from "vue";
const props = defineProps<NodeViewProps>();

const textAlign = computed({
  get: () => {
    return props.node?.attrs.textAlign;
  },
  set: (textAlign: string) => {
    props.updateAttributes({ textAlign: textAlign });
  },
});

const showTitle = computed(() => {
  return props.node.attrs.showTitle;
});

const titleText = computed({
  get: () => {
    return props.node?.attrs.titleText || '订阅最新内容推送';
  },
  set: (titleText: string) => {
    props.updateAttributes({ titleText: titleText });
  },
});
const showMultiline = computed(() => {
  return props.node.attrs.showMultiline;
});
</script>

<template>
  <node-view-wrapper 
    as="div" 
    :class="[
      ':uno: rounded overflow-hidden mt-3',
      selected ? ':uno: ring-2' : ':uno: ring-1 ring-gray-200'
    ]">

    <div class=":uno: p-2.5">
      <follow-card 
        :text-align="textAlign"
        :show-title="showTitle"
        :title-text="titleText" 
        :show-multiline="showMultiline"
      />
    </div>
    
  </node-view-wrapper>
</template>
