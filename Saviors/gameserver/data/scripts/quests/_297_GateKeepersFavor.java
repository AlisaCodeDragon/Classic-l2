package quests;

import studio.lineage2.gameserver.model.instances.NpcInstance;
import studio.lineage2.gameserver.model.quest.Quest;
import studio.lineage2.gameserver.model.quest.QuestState;

public final class _297_GateKeepersFavor extends Quest
{

	private static final int STARSTONE = 1573;

	public _297_GateKeepersFavor()
	{
		super(PARTY_NONE, REPEATABLE);
		addStartNpc(30540);

		addTalkId(30540);

		addKillId(20521);

		addQuestItem(STARSTONE);

		addLevelCheck(15, 21);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("gatekeeper_wirphy_q0297_03.htm"))
		{
			st.setCond(1);
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int npcId = npc.getNpcId();
		int cond = st.getCond();

		switch(npcId)
		{
			case 30540:
				if(cond == 0)
				{
					if(checkStartCondition(st.getPlayer()))
						htmltext = "gatekeeper_wirphy_q0297_02.htm";
					else
						htmltext = "gatekeeper_wirphy_q0297_01.htm";
				}
				else if(cond == 1)
					htmltext = "gatekeeper_wirphy_q0297_04.htm";
				else if(cond == 2)
				{
					htmltext = "gatekeeper_wirphy_q0297_05.htm";
					st.takeItems(STARSTONE, -1);
					st.giveItems(736, 2);
					st.finishQuest();
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(st.getCond() == 1)
		{
			st.rollAndGive(STARSTONE, 1, 1, 20, 33);
			if(st.getQuestItemsCount(STARSTONE) >= 20)
				st.setCond(2);
		}
		return null;
	}
}