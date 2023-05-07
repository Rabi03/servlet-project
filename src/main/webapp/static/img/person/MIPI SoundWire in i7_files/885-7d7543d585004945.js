"use strict";(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[885],{77064:function(e,r,n){n.d(r,{u:function(){return c}});var a=n(14806),s=n(35250),t=n(36963),i=n(19841);n(70079);var l=n(34303);function o(){var e=(0,a.Z)(["relative top-[-3px] h-2 w-2 rotate-45 transform border-r border-b border-black/10 bg-gray-100"]);return o=function(){return e},e}var c=function(e){var r=e.label,n=e.side,a=e.sideOffset,l=e.withArrow,o=e.wide,c=e.interactive,u=e.usePortal,p=e.children,m=(0,s.jsxs)(t.VY,{side:void 0===n?"bottom":n,sideOffset:void 0===a?14:a,className:(0,i.Z)("relative rounded border border-black/10 bg-gray-100 p-1 transition-opacity",void 0!==o&&o?"max-w-sm":"max-w-xs"),children:[(0,s.jsx)("span",{className:"flex items-center whitespace-pre-wrap py-1 px-2 text-center text-sm font-medium normal-case text-gray-700",children:r}),(void 0===l||l)&&(0,s.jsx)(t.Eh,{asChild:!0,children:(0,s.jsx)(d,{})})]});return(0,s.jsxs)(t.fC,{delayDuration:300,disableHoverableContent:!(void 0!==c&&c),children:[(0,s.jsx)(t.xz,{asChild:!0,children:(0,s.jsx)("span",{children:p})}),void 0===u||u?(0,s.jsx)(t.h_,{children:m}):m]})},d=l.Z.div(o())},86885:function(e,r,n){n.d(r,{Z:function(){return y}});var a=n(61706),s=n(45813),t=n(35250),i=n(54721),l=n.n(i),o=n(61432),c=n(70079),d=n(1454),u=n(12762),p=n(98943),m=n(37952),x=n(66285),f=n(74516),h=n(35e3),b=n(69858),v=n(21345),g=n(77507);function y(e){var r=e.isOpen,n=e.onClose,i=(0,c.useRef)(null),y=(0,x.hz)(),j=(0,u.WS)(),w=(0,c.useState)(!1),k=w[0],N=w[1],C=(0,o.useRouter)(),Z=(0,c.useCallback)(function(){j(p.s6.closeAccountPaymentModal),n()},[n,j]),P=(0,c.useCallback)((0,a.Z)(function(){var e;return(0,s.__generator)(this,function(r){switch(r.label){case 0:N(!0),j(p.s6.clickAccountPaymentCheckout),r.label=1;case 1:return r.trys.push([1,3,4,5]),[4,m.ZP.getCheckoutLink()];case 2:return e=r.sent(),C.push(e.url),[3,5];case 3:return r.sent(),f.m.warning("The payments page encountered an error. Please try again. If the problem continues, please visit help.openai.com.",{hasCloseButton:!0}),[3,5];case 4:return N(!1),[7];case 5:return[2]}})}),[C,j,N]),A=(0,c.useCallback)((0,a.Z)(function(){var e;return(0,s.__generator)(this,function(r){switch(r.label){case 0:N(!0),j(p.s6.clickAccountCustomerPortal),r.label=1;case 1:return r.trys.push([1,3,4,5]),[4,m.ZP.fetchCustomerPortalUrl()];case 2:return e=r.sent(),C.push(e.url),[3,5];case 3:return r.sent(),f.m.warning("The account management page encountered an error. Please try again. If the problem continues, please visit help.openai.com.",{hasCloseButton:!0}),[3,5];case 4:return N(!1),[7];case 5:return[2]}})}),[C,j,N]),S=(0,c.useCallback)(function(){j(p.s6.clickAccountPaymentGetHelp)},[j]),T=(0,x.mA)(function(e){var r;return null===(r=e.accountPlan)||void 0===r?void 0:r.hasCustomerObject}),_=y.has("disable_upgrade_ui");return(0,t.jsxs)(h.x,{isOpen:r,onClose:n,focusRef:i,children:[(0,t.jsxs)("div",{className:"flex w-full flex-row items-center justify-between border-b py-3 px-4 dark:border-gray-700",children:[(0,t.jsx)("span",{className:"text-base font-semibold sm:text-base",children:"Your plan"}),(0,t.jsx)("button",{className:"text-gray-700 opacity-50 transition hover:opacity-75 dark:text-white",onClick:Z,children:(0,t.jsx)(d.q5L,{className:"h-6 w-6"})})]}),(0,t.jsxs)("div",{className:"grid sm:grid-cols-2",children:[(0,t.jsx)("div",{className:"relative order-2 col-span-1 border-t border-r-0 dark:border-gray-700 sm:order-1 sm:border-t-0 sm:border-r",children:(0,t.jsx)(b.Oi,{rowElements:[(0,t.jsx)(b.Cu,{text:"Free plan"},"row-free-plan-name"),(0,t.jsx)(b.hi,{variant:"disabled",disabled:!0,text:g.S.free.callToAction.active},"row-free-plan-button"),(0,t.jsx)(b.G,{variant:"secondary",text:g.S.free.demandAccess},"row-free-plan-demand"),(0,t.jsx)(b.G,{variant:"secondary",text:g.S.free.responseSpeed},"row-free-plan-speed"),(0,t.jsx)(b.G,{className:"sm:pb-2",variant:"secondary",text:g.S.free.modelFeatures},"row-free-plan-updates"),]})}),(0,t.jsx)("div",{className:"relative order-1 col-span-1 sm:order-2",children:(0,t.jsx)(b.Oi,{rowElements:[(0,t.jsx)(b.Cu,{text:g.S.plus.name,children:(0,t.jsx)("span",{className:"font-semibold text-gray-500",children:g.S.plus.costInDollars})},"row-plus-plan-title"),(0,t.jsx)(b.hi,{variant:"primary",disabledText:_?"Due to high demand, we've temporarily paused upgrades.":"",disabled:k,isLoading:k,ref:i,onClick:P,text:g.S.plus.callToAction.inactivePayment},"row-plus-plan-button"),(0,t.jsx)(b.G,{variant:"primary",text:g.S.plus.demandAccess},"row-plus-plan-demand"),(0,t.jsx)(b.G,{variant:"primary",text:g.S.plus.responseSpeed},"row-plus-plan-speed"),(0,t.jsx)(b.G,{className:"sm:pb-2",variant:"primary",text:g.S.plus.modelFeatures},"row-plus-plan-updates"),T&&(0,t.jsx)(b.nR,{className:"sm:pb-1",isLoading:k,text:g.S.manageSubscription.callToAction,onClick:A},"row-plus-plan-manage"),(0,t.jsx)(l(),{target:"_blank",href:v.L,passHref:!0,children:(0,t.jsx)(b.nR,{className:"sm:pb-1",isLoading:!1,text:g.S.getHelp.callToAction,onClick:S},"row-plus-plan-help")},"row-plus-plan-help-link"),]})})]})]})}},35e3:function(e,r,n){n.d(r,{x:function(){return o}});var a=n(14806),s=n(35250),t=n(34303),i=n(73925);function l(){var e=(0,a.Z)(["flex grow items-center justify-center bg-white dark:bg-gray-900 rounded-md flex flex-col items-start overflow-hidden border shadow-md dark:border-gray-700"]);return l=function(){return e},e}var o=function(e){var r=e.children,n=e.isOpen,a=e.onClose,t=e.focusRef;return(0,s.jsx)(i.ZP,{size:"fullscreen",isOpen:n,onModalClose:a,type:"success",title:"",className:"bg-transparent dark:bg-transparent",initialFocusRef:t,children:(0,s.jsx)("div",{className:"flex h-full flex-col items-center justify-start",children:(0,s.jsx)("div",{className:"relative",children:(0,s.jsx)(c,{children:r})})})})},c=t.Z.div(l())},69858:function(e,r,n){n.d(r,{Cu:function(){return h},G:function(){return g},Oi:function(){return f},hi:function(){return v},nR:function(){return y}});var a=n(14806),s=n(35250),t=n(19841),i=n(70079),l=n(1454),o=n(34303),c=n(39690),d=n(79876),u=n(77064);function p(){var e=(0,a.Z)(["p-4 flex flex-col gap-3 bg-white z-20 relative dark:bg-gray-900"]);return p=function(){return e},e}function m(){var e=(0,a.Z)(["gap-2 flex flex-row justify-start items-center text-sm"]);return m=function(){return e},e}function x(){var e=(0,a.Z)(["text-xl font-semibold justify-between items-center flex"]);return x=function(){return e},e}var f=function(e){var r=e.rowElements;return(0,s.jsx)(j,{children:r.map(function(e){return e})})},h=function(e){var r=e.className,n=e.text,a=e.children;return(0,s.jsxs)(k,{className:r,children:[(0,s.jsx)("span",{children:n}),a]})},b={"primary-disabled":"border-none bg-gray-200 py-3 font-semibold hover:bg-gray-200",primary:"border-none py-3 font-semibold",disabled:"dark:text-gray-white border-none bg-gray-300 py-3 font-semibold text-gray-800 hover:bg-gray-300 dark:bg-gray-500 dark:opacity-100"},v=(0,i.forwardRef)(function(e,r){var n=e.isLoading,a=void 0!==n&&n,i=e.disabled,o=e.onClick,p=e.variant,m=void 0===p?"primary":p,x=e.text,f=e.disabledText;return f?(0,s.jsx)("div",{className:"relative",children:(0,s.jsx)(u.u,{side:"bottom",sideOffset:20,label:f,usePortal:!1,children:(0,s.jsxs)(c.z,{ref:r,as:"button",color:"disabled",className:(0,t.Z)("w-full",b[m]),children:[x,a&&(0,s.jsx)(d.ZP,{className:"animate-spin",icon:l.dAq})]})})}):(0,s.jsxs)(c.z,{disabled:void 0!==i&&i,onClick:o,openNewTab:!0,ref:r,as:"button",className:(0,t.Z)(b[m]),children:[(0,s.jsx)("span",{className:(0,t.Z)("inline-block",{"text-gray-700":"primary-disabled"===m,"text-white":"primary"===m}),children:x}),a&&(0,s.jsx)(d.ZP,{className:"animate-spin",icon:l.dAq})]})});v.displayName="PricingPlanButton";var g=function(e){var r=e.variant,n=void 0===r?"primary":r,a=e.className,i=e.text;return(0,s.jsxs)(w,{className:a,children:[(0,s.jsx)(d.ZP,{icon:l._rq,className:(0,t.Z)("h-5 w-5",{"text-green-700":"primary"==n,"text-gray-400":"secondary"==n})}),(0,s.jsx)("span",{children:i})]})},y=function(e){var r=e.className,n=e.text,a=e.isLoading,t=e.onClick;return(0,s.jsx)(w,{className:r,children:(0,s.jsxs)("button",{onClick:t,className:"flex flex-row items-center space-x-1 underline",children:[(0,s.jsx)("span",{children:n}),a&&(0,s.jsx)(d.ZP,{className:"animate-spin",icon:l.dAq})]})})},j=o.Z.div(p()),w=o.Z.div(m()),k=o.Z.div(x())},21345:function(e,r,n){n.d(r,{L:function(){return a}});var a="https://help.openai.com/en/collections/3943089-billing"},77507:function(e,r,n){n.d(r,{S:function(){return a}});var a={free:{name:"Free plan",callToAction:{active:"Your current plan",inactive:"Your current plan"},costInDollars:"",demandAccess:"Available when demand is low",responseSpeed:"Standard response speed",modelFeatures:"Regular model updates"},plus:{name:"ChatGPT Plus",callToAction:{active:"Your current plan",inactive:"I'm interested",inactivePayment:"Upgrade plan"},costInDollars:"USD $20/mo",demandAccess:"Available even when demand is high",responseSpeed:"Faster response speed",modelFeatures:"Priority access to new features"},manageSubscription:{callToAction:"Manage my subscription"},getHelp:{callToAction:"I need help with a billing issue"}}}}]);